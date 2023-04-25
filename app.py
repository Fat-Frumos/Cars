from flask import Flask, jsonify, request
import nltk

app = Flask(__name__)

@app.route('/paraphrase', methods=['GET'])
def paraphrase():

    tree_str = request.args.get('tree')
    if not tree_str:
        return jsonify({'error': 'tree parameter is required'}), 400


    try:
        tree = nltk.Tree.fromstring(tree_str)
    except ValueError as e:
        return jsonify({'error': str(e)}), 400


    limit = request.args.get('limit', default=20, type=int)


    nps = []
    for subtree in tree.subtrees():
        if subtree.label() == 'NP':
            nps.append(subtree)


    paraphrases = []
    for i, np in enumerate(nps):
        for j in range(i+1, len(nps)):
            new_tree = tree.copy(deep=True)
            np1, np2 = new_tree.leaf_treeposition(np.leaves()[0]), new_tree.leaf_treeposition(nps[j].leaves()[0])
            new_tree[np1[:-1]][np1[-1]] = nps[j]
            new_tree[np2[:-1]][np2[-1]] = np
            paraphrase_str = str(new_tree)
            if paraphrase_str not in paraphrases and len(paraphrases) < limit:
                paraphrases.append(paraphrase_str)


    return jsonify({'paraphrases': [{'tree': p} for p in paraphrases]}), 200

if __name__ == '__main__':
    app.run()

import os

from flask import Flask, jsonify, request
from nltk.tree import Tree

app = Flask(__name__)

@app.route('/paraphrase', methods=['GET'])
def paraphrase():
    tree_string = request.args.get('tree', '')
    tree = Tree.fromstring(tree_string)
    limit = int(request.args.get('limit', 20))
    nps = [subtree for subtree in tree.subtrees(lambda t: t.label() == 'NP')]


    paraphrases = []
    for i, np in enumerate(nps):
        for j in range(i + 1, len(nps)):
            new_tree = tree.copy(deep=True)
            new_np = nps[j].copy(deep=True)
            np_index = new_tree.leaf_treeposition(np[0].index())
            new_np_index = new_tree.leaf_treeposition(new_np[0].index())
            new_tree[0][np_index[:-1]][np_index[-1]] = new_np
            new_tree[0][new_np_index[:-1]][new_np_index[-1]] = np
            paraphrases.append(str(new_tree))

    return jsonify(paraphrases[:limit])

if __name__ == '__main__':
    app.run(debug=True)    
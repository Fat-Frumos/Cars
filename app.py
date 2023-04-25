from flask import Flask, request, jsonify
from nltk.tree import Tree

app = Flask(__name__)

@app.route('/paraphrase', methods=['GET'])
def paraphrase():
    # Get the required 'tree' parameter from the query string
    tree_str = request.args.get('tree')
    if not tree_str:
        return jsonify({'error': 'Missing "tree" parameter.'}), 400
    
    # Parse the tree string into an nltk Tree object
    try:
        tree = Tree.fromstring(tree_str)
    except ValueError:
        return jsonify({'error': 'Invalid tree string.'}), 400
    
    # Get the optional 'limit' parameter from the query string
    limit = int(request.args.get('limit', 20))
    
    # Find all NPs in the tree
    nps = find_nps(tree)
    
    # Generate paraphrased versions by swapping child NPs
    paraphrases = generate_paraphrases(tree, nps, limit)
    
    # Return the paraphrases as a list in JSON format
    return jsonify({'paraphrases': [{'tree': str(p)} for p in paraphrases]})


def find_nps(tree):
    nps = []
    for subtree in tree.subtrees(filter=lambda t: t.label() == 'NP'):
        np_str = ' '.join(subtree.leaves())
        nps.append(np_str)
    return nps


def generate_paraphrases(tree, nps, limit):
    paraphrases = set()
    for i, np1 in enumerate(nps):
        for j, np2 in enumerate(nps[i+1:], i+1):
            new_tree = tree.copy(deep=True)
            np1_subtree, np2_subtree = find_np_subtrees(new_tree, np1, np2)
            if np1_subtree and np2_subtree:
                swap_nps(np1_subtree, np2_subtree)
                paraphrased_tree = new_tree
                paraphrases.add(paraphrased_tree)
                if len(paraphrases) >= limit:
                    return paraphrases
    return paraphrases


def find_np_subtrees(tree, np1, np2):
    np1_subtree = None
    np2_subtree = None
    for subtree in tree.subtrees(filter=lambda t: t.label() == 'NP'):
        np_str = ' '.join(subtree.leaves())
        if np_str == np1:
            np1_subtree = subtree
        elif np_str == np2:
            np2_subtree = subtree
        if np1_subtree and np2_subtree:
            break
    return np1_subtree, np2_subtree


def swap_nps(np1_subtree, np2_subtree):
    np1_subtree_parent = np1_subtree.parent()
    np2_subtree_parent = np2_subtree.parent()
    np1_subtree_index = np1_subtree_parent.index(np1_subtree)
    np2_subtree_index = np2_subtree_parent.index(np2_subtree)
    np1_subtree_parent[np1_subtree_index] = np2_subtree
    np2_subtree_parent[np2_subtree_index] = np1_subtree

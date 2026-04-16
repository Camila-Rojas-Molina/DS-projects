"""
Depth first search : It goes as deep as possible 
along each branch before backtracking/exploring other branches

It's a graph traversal, it uses a stack often implmeented with recursion
and it's used in algs such as finding cycles 
"""

class Graph:
    def __init__(self, adj):
        self._adj = adj

    def size(self):
        return len(self._adj)

    def neighbors(self, v):
        return self._adj[v]


# Create G FIRST (replace this with real graph)
G = Graph([
    [1, 2],   # neighbors of 0
    [3],      # neighbors of 1
    [3],      # neighbors of 2
    []        # neighbors of 3
])

marked = [False] * G.size()
order = []

def visit(v):
    order.append(v)   # or: print(v)

def dfs(G, v):
    visit(v)
    marked[v] = True
    for w in G.neighbors(v):
        if not marked[w]:
            dfs(G, w)


if __name__ == "__main__":
    dfs(G, 0)
    print(order)
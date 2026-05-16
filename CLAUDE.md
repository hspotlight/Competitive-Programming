# Competitive Programming

Source code archive of competitive programming solutions written during university.

## Structure

```
Codeforces/   # Codeforces problems (Div 2 A-level)
UVa/          # UVa Online Judge problems (~259 solutions)
```

Each problem lives in its own directory:
```
UVa/10034 - Freckles/
  src/Main.java   # solution
  input.txt       # sample test case
```

## Language

All solutions are Java. Each `Main.java` follows this template:

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    void run() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // solution
    }
}
```

## Running a Solution

```bash
cd "UVa/10034 - Freckles/src"
javac Main.java
java Main < ../input.txt
```

## Algorithm Categories

| Category | Examples |
|----------|---------|
| Graph / MST | Kruskal, Prim, second-best MST, bottleneck MST |
| Shortest Path | Dijkstra (single + double pass), Floyd-Warshall |
| Network Flow | Ford-Fulkerson, bipartite matching |
| Graph Properties | Articulation points, SCC, Eulerian path, cactus |
| Dynamic Programming | Bitmask DP, memoization |
| Number Theory | BigInteger, modular arithmetic, prime sieve |
| String Processing | Edit distance, anagram, word transformation |
| Search | BFS, DFS, backtracking |

## Gaps (Not Covered)

| Area | Missing |
|------|---------|
| String Algorithms | KMP, Z-algorithm, Aho-Corasick, suffix arrays, Trie |
| Range Queries | Segment Tree, Fenwick Tree (BIT), Sparse Table |
| Advanced DP | Interval DP, digit DP, DP on trees/DAGs |
| Computational Geometry | Convex hull, line intersection, polygon area |
| Codeforces | Only 8 solutions, all Div 2 A-level (easiest tier) |

## Conventions

- Inner classes (`Edge`, `Node`, `UnionFind`) are defined inside `Main`
- `Comparable<T>` used for custom ordering in priority queues
- `StreamTokenizer` used in some older solutions for faster input parsing
- `System.setIn(new FileInputStream("input.txt"))` may appear in local testing — remove before submitting

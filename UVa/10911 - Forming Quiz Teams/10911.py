import math

# UVa 10911 - Forming Quiz Teams
# Time limit exceed
x_coord = []
y_coord = []
distance = []
last_node = 0
max_max = 131072
full_state = 0
initial_state = 0
dp = []

def cal_distance(x1, y1, x2, y2):
    return math.sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2))

def cal_distance_all_node():
    for i in range(1, last_node):
        for j in range(i + 1, last_node):
            distance[j][i] = distance[i][j] = cal_distance(x_coord[i], y_coord[i], x_coord[j], y_coord[j])

def cal_min_distance(state):
    if (state == full_state):
        return 0

    if (dp[state] != -1):
        return dp[state]
    
    answer = 9999999999
    for i in range(1, last_node):
        if (state & (1 << i) > 0):
            continue
        for j in range(i + 1, last_node):
            if (state & (1 << j) > 0):
                continue
            new_state = state | (1 << i) | (1 << j)
            new_answer = distance[i][j] + cal_min_distance(new_state)
            answer = min(answer, new_answer)
    dp[state] = answer
    return answer

if __name__ == "__main__":
    case_counter = 0
    while(True):
        n = int(input())
        if (n == 0):
            break

        last_node = 2 * n + 1
        full_state = pow(2, 2 * n + 1) - 2
        dp = [-1 for i in range(0, max_max + 1)]
        x_coord = [0 for i in range(0, last_node)]
        y_coord = [0 for i in range(0, last_node)]
        distance = [[0 for i in range(0, last_node)] for i in range(0, last_node)]

        for i in range (1, last_node):
            line = input()
            tokens = line.split()
            x_coord[i] = int(tokens[1])
            y_coord[i] = int(tokens[2])

        case_counter += 1
        cal_distance_all_node()
        ans = cal_min_distance(initial_state)
        print("Case " + str(case_counter) + ": " + "{0:.2f}".format(ans))
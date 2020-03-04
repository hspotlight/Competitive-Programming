def get_pair_number():
    line = input()
    return [int(v) for v in line.split()]


def add_dependencies(dependencies_list, dependency_count, src, des):
    dependencies_list[src] = dependencies_list[src] + [des]
    dependency_count[des] = dependency_count[des] + 1
    return [dependencies_list, dependency_count]


def get_task_with_no_dependency(dependency_count):
    task_queue = []
    for task in range(1, len(dependency_count)):
        if (dependency_count[task] == 0):
            task_queue += [task]
    return task_queue


def update_dependency_count(depended_task, dependency_count, task):
    for next_task in depended_task:
        dependency_count[next_task] -= 1
    return dependency_count


def get_next_executable_task(depended_task, dependency_count, task):
    executable_task = []
    for next_task in depended_task:
        if (dependency_count[next_task] == 0):
            executable_task += [next_task]
    return executable_task


def get_ordered_tasks(dependencies_list, dependency_count):
    task_queue = get_task_with_no_dependency(dependency_count)

    n_finished_tasks = 0
    ordered_tasks = []
    while(len(task_queue) > 0):
        task = task_queue.pop(0)
        ordered_tasks += [task]

        dependency_count = update_dependency_count(
            dependencies_list[task], dependency_count, task)
        task_queue += get_next_executable_task(
            dependencies_list[task], dependency_count, task)

    return " ".join([str(v) for v in ordered_tasks])


if __name__ == "__main__":
    while(True):
        [n_nodes, n_edges] = get_pair_number()
        if (n_nodes == 0 and n_nodes == 0):
            break

        dependencies_list = [[] for i in range(0, n_nodes + 1)]
        dependency_count = [0 for i in range(0, n_nodes + 1)]

        for i in range(0, n_edges):
            [src, des] = get_pair_number()
            [dependencies_list, dependency_count] = add_dependencies(
                dependencies_list, dependency_count, src, des)

        ordered_tasks = get_ordered_tasks(dependencies_list, dependency_count)
        print(ordered_tasks)

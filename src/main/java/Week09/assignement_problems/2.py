
def longestStreak(costs, budget):
    left = 0
    current_sum = 0
    best_length = 0
    best_start = -1

    for right in range(len(costs)):
        current_sum += costs[right]

        while current_sum > budget:
            current_sum -= costs[left]
            left += 1

        length = right - left + 1

        if length > best_length:
            best_length = length
            best_start = left

    return (best_length, best_start)


costs = [4, 2, 1, 7, 3, 1, 2, 1, 5]
budget = 8

print(longestStreak(costs, budget))
# Output: (4, 4)


def countPeriods(transactions, k):
    prefix_sum = 0
    count = 0
    frequency = {0: 1}

    for value in transactions:
        prefix_sum += value

        count += frequency.get(prefix_sum - k, 0)

        frequency[prefix_sum] = (
            frequency.get(prefix_sum, 0) + 1
        )

    return count


transactions = [3, 4, -7, 1, 3, 3, 1, -4]
k = 7

print(countPeriods(transactions, k))
# Output: 4


def maxSumSubarray(sales, k):
    window_sum = sum(sales[:k])
    maximum = window_sum

    for i in range(k, len(sales)):
        window_sum += sales[i] - sales[i - k]
        maximum = max(maximum, window_sum)

    return maximum


sales = [2, 1, 5, 1, 3, 2]
k = 3

print(maxSumSubarray(sales, k))
# Output: 9

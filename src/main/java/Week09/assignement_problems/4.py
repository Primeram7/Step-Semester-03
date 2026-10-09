
def lowerBound(scores, target):
    left = 0
    right = len(scores)

    while left < right:
        mid = (left + right) // 2

        if scores[mid] < target:
            left = mid + 1
        else:
            right = mid

    return left


def countInBand(scores, low, high):
    return lowerBound(scores, high + 1) - lowerBound(scores, low)


scores = [35, 42, 42, 50, 58, 58, 58, 63, 71, 88]

print(countInBand(scores, 42, 58))
# Output: 6

print(countInBand(scores, 90, 100))
# Output: 0


def auditRoute(grid):
    if not grid or not grid[0]:
        return []

    top = 0
    bottom = len(grid) - 1
    left = 0
    right = len(grid[0]) - 1
    result = []

    while top <= bottom and left <= right:

        # Traverse the top row
        for col in range(left, right + 1):
            result.append(grid[top][col])
        top += 1

        # Traverse the right column
        for row in range(top, bottom + 1):
            result.append(grid[row][right])
        right -= 1

        # Traverse the bottom row
        if top <= bottom:
            for col in range(right, left - 1, -1):
                result.append(grid[bottom][col])
            bottom -= 1

        # Traverse the left column
        if left <= right:
            for row in range(bottom, top - 1, -1):
                result.append(grid[row][left])
            left += 1

    return result


grid = [
    [1, 2, 3, 4],
    [5, 6, 7, 8],
    [9, 10, 11, 12]
]

print(auditRoute(grid))
# Output: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

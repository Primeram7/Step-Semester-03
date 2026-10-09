
def warehouseSummary(grid):
    total = 0
    max_value = grid[0][0]
    max_coordinate = (0, 0)

    for row in range(len(grid)):
        for col in range(len(grid[row])):
            value = grid[row][col]
            total += value

            if value > max_value:
                max_value = value
                max_coordinate = (row, col)

    return total, max_coordinate


grid = [
    [4, 9, 2],
    [7, 1, 6],
    [3, 12, 5]
]

total, coordinate = warehouseSummary(grid)
print("Total:", total)
print("Maximum coordinate:", coordinate)

# Output:
# Total: 49
# Maximum coordinate: (2, 1)

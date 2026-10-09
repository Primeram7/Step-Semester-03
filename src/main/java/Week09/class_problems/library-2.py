
def findBook(catalog, targetIsbn):
    left = 0
    right = len(catalog) - 1

    while left <= right:
        mid = (left + right) // 2
        isbn, title = catalog[mid]

        if isbn == targetIsbn:
            return title
        elif isbn < targetIsbn:
            left = mid + 1
        else:
            right = mid - 1

    return "Not Found"


catalog = [
    ("0001112223", "Introduction to Algebra"),
    ("0002223334", "Beginning Python"),
    ("0003334445", "Classic Mythology"),
    ("0004445556", "Data and Society"),
    ("0005556667", "European History")
]

print(findBook(catalog, "0003334445"))
# Output: Classic Mythology

print(findBook(catalog, "0009998887"))
# Output: Not Found

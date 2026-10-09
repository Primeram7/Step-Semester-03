
def footfallReport(visitors, queries):
    prefix = [0]

    for value in visitors:
        prefix.append(prefix[-1] + value)

    result = []

    for start, end in queries:
        result.append(prefix[end + 1] - prefix[start])

    return result


visitors = [12, 7, 3, 9, 15, 4, 8]
queries = [(0, 2), (2, 5), (4, 6), (3, 3)]

print(footfallReport(visitors, queries))

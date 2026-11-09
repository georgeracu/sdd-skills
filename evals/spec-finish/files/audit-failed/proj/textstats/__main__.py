import sys

from textstats.count import count_words


def main(argv: list[str]) -> int:
    path = argv[0]
    try:
        with open(path, encoding="utf-8") as f:
            text = f.read()
    except FileNotFoundError:
        print(f"file not found: {path}", file=sys.stderr)
        return 2
    except IsADirectoryError:
        print(f"not a file: {path}", file=sys.stderr)
        return 2
    print(f"words: {count_words(text)}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))

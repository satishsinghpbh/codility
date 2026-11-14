# Review rules for codility

Org baseline (OWASP ASVS / NIST SSDF mapped, from the `engineering-ai` plugin) applies. Repo-specific rules below.

## Blocking
- Solution does not compile with `javac`.
- Wrong result for the problem's stated examples or edge cases (empty input, single element, min/max values, integer overflow).
- Complexity worse than the problem's required bound.

## Non-blocking
- Missing complexity comment.
- Naming and readability.

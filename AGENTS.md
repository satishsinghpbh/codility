# codility

Standalone Java solutions to Codility exercises (one file per problem, no build tool).

Org-wide standards come from the `engineering-ai` Devin plugin; this file adds repo-specific rules. Review rules: `.github/REVIEW.md`.

## Setup
- Runtime: JDK 21+. No Maven/Gradle; no dependencies.

## Commands
- Compile and run one file: `java <File>.java` (single-file source launch).
- Compile all: `javac -d out *.java`

## Repo-specific rules
- One problem per file; the file name describes the problem.
- No external libraries; standard library only.
- Each solution includes a `main` method or comments with sample inputs and expected outputs.
- State time and space complexity in a comment at the top of the solution.

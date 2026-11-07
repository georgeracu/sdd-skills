# A glossary or ubiquitous-language generator

**What was asked:** a skill that writes or maintains a project's glossary or `UBIQUITOUS_LANGUAGE.md`.

**Why it is out:** the pipeline only reads the glossary. `spec-discovery` and `spec-generator` reuse its terms, and `spec-design-review` flags an identifier that differs from them. Writing the glossary is a separate job that other skills already do.

**What to use instead:** produce `UBIQUITOUS_LANGUAGE.md` with a skill made for it, anywhere in the project. The pipeline finds it with the pattern `**/UBIQUITOUS_LANGUAGE.md`.

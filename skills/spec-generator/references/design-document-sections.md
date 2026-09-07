# Design Document Sections

The `design.md` must be comprehensive and implementation-ready. Include the following sections (adapt based on what's relevant to the feature):

## Required Sections

1. **Overview** -- Technical overview of the feature, architectural approach, and how it integrates with the existing system.

2. **Key Design Decisions** -- Rationale for major technical choices, trade-offs considered, and alternatives rejected.

3. **Architecture** -- System architecture with Mermaid diagrams showing components, relationships, and data flow.

4. **Components and Interfaces** -- Component descriptions with interface definitions in the project's language (Kotlin/TypeScript).

5. **Data Models** -- Data structures, database schemas (DynamoDB tables, indexes, keys), and DTOs with all fields and types.

6. **Correctness Properties** -- Universal statements about system behavior:
   - Format: `_For any_ [input], [behavior] SHALL [hold true]`
   - Each property traces back: `**Validates: Requirements N.N**`

7. **Error Handling** -- Error classification, HTTP status codes, error response format, retry strategies, and graceful degradation.

8. **Testing Strategy** -- Unit tests, property-based tests, integration tests, and test data requirements.

## Sections to Include When Applicable

9. **API Specifications** (when the feature involves new or modified endpoints):
   - Complete endpoint specifications (method, path, headers)
   - Request/response schemas with all fields and types
   - Error codes and descriptions
   - Authentication requirements (IAM/SigV4, guest vs registered)
   - Alignment with existing `openapi.yaml` patterns

10. **Sequence Diagrams** (when the feature has multi-component interactions):
    - Happy path flows using Mermaid syntax
    - Error handling scenarios
    - Complex interaction patterns (e.g., Lambda -> DynamoDB -> S3)

11. **AWS Service Integration** (when the feature uses AWS services):
    - Service configurations (Lambda, DynamoDB, S3, Bedrock, Cognito, etc.)
    - IAM policies and permissions
    - CloudFormation resource definitions
    - Infrastructure requirements

12. **Security Architecture** (when the feature handles auth, user data, or external input):
    - Authentication/authorization flows
    - Input validation strategies
    - Data encryption (in transit/at rest)
    - Data privacy and retention

13. **Performance Considerations** (when the feature has latency or throughput concerns):
    - Performance targets (p50, p95, p99 if applicable)
    - Caching strategies
    - Concurrent processing patterns
    - Database optimization (indexes, query patterns)

## Design Quality Standards

- Include **concrete examples** (e.g., S3 key: `user-123/2025/01/scan-xyz.jpg`, DynamoDB item structure)
- Provide **complete data class definitions** with all fields and types
- Specify **exact error codes and messages**
- Document **trade-offs and alternatives considered**
- Reference **existing code/documentation** where building on existing systems
- Use **consistent terminology** from the project's ubiquitous language
- Align with **existing API patterns** from `openapi.yaml`

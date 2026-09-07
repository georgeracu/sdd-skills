# Design Document: Profile Summary

## Overview

ProfileSummaryHandler reads the Account repository and returns ProfileSummary as JSON.

## Components and Interfaces

- **ProfileSummaryHandler**: http4k handler for `GET /accounts/{accountId}/profile/summary`.
- **ProfileSummary**: API DTO containing accountId and displayName.

## Testing Strategy

Unit-test the service and integration-test the handler response.

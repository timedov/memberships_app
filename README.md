# Memberships App

This project is a membership-based application designed to enable users to create, view, and interact with content. It allows users to subscribe to creators, purchase individual posts, and explore content through a personalized feed. The app is built to facilitate seamless content sharing and monetization, with Firebase serving as the backend for real-time data management.

## Features

- **Feed**: View a feed of recent posts with categories and sorting options.
- **Posts**: Create and view posts (both free and paid). Paid posts are blurred and require a subscription or purchase to unlock.
- **Comments**: Add and view comments on posts.
- **Subscriptions**: Subscribe to creators to access their exclusive content.
- **Profile**: Manage your profile, subscriptions, and created posts.
- **Authentication**: Login with fingerprint support.

## Tech Stack

- **Min SDK**: 24
- **Language**: Kotlin
- **Architecture**: MVI
- **Concurrency**: Coroutines
- **Dependency Injection**: Dagger
- **UI**: Activity + Fragments + Jetpack Compose
- **Database**: Room
- **Version Control**: Gitflow

## Project Structure

The project is divided into modules:
- **Core**: Shared functionality and utilities.
- **Features**: Individual features like feed, profile, posts, etc.

## Tests in Modules

Tests are implemented in the following feature modules:
- `:features:commentreplies`
- `:features:feed`
- `:features:postdetails`
- `:features:profile`
- `:features:savepost`
- `:features:signin`
- `:features:uploadpost`

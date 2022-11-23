# Contributing to ngtor

First off, thanks for taking the time to contribute!

Please take a moment to review this document in order to make the contribution
process easy and effective for everyone involved.

These are just guidelines, not rules, use your best judgement and feel free to propose changes 
to this document in a pull request.

Following these guidelines helps to communicate that you respect the time of
the developers managing and developing this open source project. In return,
they should reciprocate that respect in addressing your issue or assessing
patches and features.

## Using the Issue Tracker

The [issue tracker](https://github.com/theborakompanioni/ngtor/issues) is
the preferred channel for [bug reports](#bug-reports), [features requests](#feature-requests)
and [submitting pull requests](#pull-requests), but please respect the following
restrictions:

* Please **do not** derail or troll issues. Keep the discussion on topic and
  respect the opinions of others.

* Please **do not** submit an issue unless you have checked that your problem has
  already been reported.

## Bug Reports

A bug is a _demonstrable problem_ that is caused by the code in the repository.
Good bug reports are extremely helpful, so thanks!

Guidelines for bug reports:

1. **Use the GitHub issue search** &mdash; check if the issue has already been
   reported.

2. **Check if the issue has been fixed** &mdash; try to reproduce it using the
   latest `master` or development branch in the repository.

3. **Isolate the problem** &mdash; ideally create a [reduced test
   case](https://css-tricks.com/reduced-test-cases/) and a live example.


A good bug report shouldn't leave others needing to chase you up for more
information. Please try to be as detailed as possible in your report. What is
your environment? What steps will reproduce the issue? What platform(s) and OS
experience the problem? Do other platforms show the bug differently? What
would you expect to be the outcome? All these details will help people to fix
any potential bugs.

Example:

> Short and descriptive example bug report title
>
> A summary of the issue and the browser/OS environment in which it occurs. If
> suitable, include the steps required to reproduce the bug.
>
> 1. This is the first step
> 2. This is the second step
> 3. Further steps, etc.
>
> `<url>` - a link to the reduced test case
>
> Any other information you want to share that is relevant to the issue being
> reported. This might include the lines of code that you have identified as
> causing the bug, and potential solutions (and your opinions on their
> merits).


## Feature Requests

Feature requests are welcome. But take a moment to find out whether your idea
fits with the scope and aims of the project. It's up to *you* to make a strong
case to convince the project's developers of the merits of this feature. Please
provide as much detail and context as possible.


## Pull Requests

Good pull requests, patches, improvements, new features are a fantastic
help. They should remain focused in scope and avoid containing unrelated
commits.

**Please ask first** before embarking on any significant pull request (e.g.
implementing features, refactoring code, porting to a different language),
otherwise you risk spending a lot of time working on something that the
project's developers might not want to merge into the project.

Please adhere to the [coding guidelines](#code-guidelines) used throughout the
project (indentation, accurate comments, etc.) and any other requirements
(such as test coverage).

Adhering to the following process is the best way to get your work
included in the project:

1. [Fork](http://help.github.com/fork-a-repo/) the project, clone your fork,
   and configure the remotes:

   ```shell script
   # Clone your fork of the repo into the current directory
   git clone https://github.com/<your-username>/ngtor.git
   # Navigate to the newly cloned directory
   cd bitcoin-spring-boot-starter
   # Assign the original repo to a remote called "upstream"
   git remote add upstream https://github.com/theborakompanioni/ngtor.git
   ```

2. If you cloned a while ago, get the latest changes from upstream:

   ```shell script
   git checkout master
   git pull upstream master
   ```

3. Create a new topic branch (off the main project development branch) to
   contain your feature, change, or fix:

   ```shell script
   git checkout -b <topic-branch-name>
   ```

4. Run all tests before committing.
   ```shell script
   ./gradlew check
   ```
   
5. Commit your changes in logical chunks. Use Git's
   [interactive rebase](https://help.github.com/articles/interactive-rebase)
   feature to tidy up your commits before making them public.

6. Locally merge (or rebase) the upstream development branch into your topic branch:

   ```shell script
   git pull [--rebase] upstream master
   ```
   
7. Push your topic branch up to your fork:

   ```shell script
   git push origin <topic-branch-name>
   ```

8. [Open a Pull Request](https://help.github.com/articles/using-pull-requests/)
    with a clear title and description against the `master` branch.
    The body should provide a meaningful commit message, which:
    - uses the imperative, present tense: "change", not "changed" or "changes".


## Code Guidelines

- Be consistent.
- Write clean code.
- Test your stuff.

## License

By contributing your code, you agree to license your contribution under the [Apache License](LICENSE).

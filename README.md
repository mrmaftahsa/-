# لقمتي — Luqmati
Arabic, right-to-left Android meal reminder app for a baby from 6 months until her first birthday. Moroccan home ingredients, offline storage, no account, ads or internet permission. Android 8+.

## What is included
- Date-of-birth setup; age-specific texture advice; browse any day.
- A transparent seven-day rotation of meal ideas, repeated through the supported age range. Not a bespoke nutritionally complete prescription or 180 unique menus.
- One, two or three meal opportunities; starts with lunch only. Parent increases frequency according to readiness. Milk guidance always visible.
- Adjustable breakfast/lunch/dinner reminder times, notification permission handling, a test notification, daily completion checkboxes.
- Local inexact alarms, restored after restart, clock/time-zone changes and app updates. No reminders after the first birthday.
- Arabic preparation and feeding guidance with WHO and NHS source links. Common allergens need deliberate introduction and continued inclusion after tolerance; the base rotation is not an allergen-introduction program.

## Build using GitHub (no Android Studio required)
1. Create a GitHub repository, preferably private.
2. Extract this ZIP. Put the CONTENTS of `baby-meals` at the repository root: `settings.gradle`, `app`, `tests`, and the hidden `.github` directory must all be included. Use GitHub Desktop to avoid missing hidden files.
3. Commit and push to `main`. In GitHub open **Actions → Build Android APK**. You can also use **Run workflow**.
4. After a successful run download **Luqmati-Android-APK** under Artifacts. Extract that ZIP and transfer `app-debug.apk` to your Android phone.
5. Open the APK, allow installation from that source if prompted, then open لقمتي. Choose the actual birth date, meal frequency and times. Enable reminders and grant notification permission. Use the test notification.

GitHub builds the app; GitHub Pages cannot run a native APK. This is a personal debug build, not a Play Store release. CI uses an ephemeral debug signing key: later builds may require uninstalling the old app, losing local settings and checkmarks. For durable updates, configure a private stable signing key before relying on long-term records.

## Local development
JDK 17, Gradle 8.9, Android SDK 35. Run `gradle assembleDebug lintDebug` from this directory (no Gradle wrapper bundled). AGP 8.7.3 is pinned.

## Validation and limitations
Manifest XML and workflow YAML were parsed successfully. Pure-Java planner tests are included but could not run locally because this environment has a Java runtime without javac; they run in the workflow. Full APK compilation and device testing must still be performed by the GitHub workflow and on a real phone; no APK is bundled. Verify notification delivery with the screen locked, after reboot, after changing times, and after denying/re-enabling permission. Android battery management can delay inexact alarms. Force-stop pauses alarms until reopening. Completion records stay on the device; no backup/export. Arabic UI has not yet been visually inspected on an Android device.

## Feeding sources (reviewed 3 November 2026)
- https://www.who.int/health-topics/complementary-feeding
- https://www.nhs.uk/baby/weaning-and-feeding/babys-first-solid-foods/
- https://www.nhs.uk/baby/weaning-and-feeding/foods-to-avoid-giving-babies-and-young-children/
- https://www.nhs.uk/best-start-in-life/baby/weaning/what-to-feed-your-baby/from-around-6-months/

The recipes are generated meal ideas informed by general guidance, not recipes endorsed by these organizations. Discuss individual feeding needs with the child's clinician. No honey before one, no added salt/sugar, safe textures and close supervision. Fully cook eggs, meat and fish. Introduce allergens individually and retain tolerated allergens regularly.

# لقمتي — Luqmati 1.2

Arabic Android meal planner for a baby from six months until her first birthday. Offline app, no account, ads, analytics or internet permission. Android 8.0+.

## Features

- Arabic app locale and explicit right-to-left containers, dialogs and text; isolated clock values to preserve number order.
- 36 rotating recipe ideas built from 16 ingredients common in Moroccan kitchens. Ingredient-specific preparation and readiness-based texture guidance.
- 12 bundled AI-generated ingredient photos; remaining ingredients use a labeled icon. Photos identify ingredients, not safe serving shapes or portions.
- Daily meal replacement, recipe search and favorites. The selected recipes feed both notifications and the shopping list.
- Foods tried/tolerated and excluded-food preferences. Recipes containing egg, dairy, wheat or fish are excluded from automatic suggestions until the parent marks that ingredient as tried/tolerated. A reaction log does not diagnose an allergy or automatically mark a food safe.
- Food diary with an editable date, food/meal name, ingredients, reaction category, liked-food flag and notes. Edit/delete entries or share a plain-text summary through the Android share sheet.
- Seven-day shopping checklist starting at the displayed date, using enabled meals and current recipe swaps. Counts mean recipe occurrences, not shopping weights or prescribed portions. Share the list through Android.
- Backup and restore through Android's file picker. `.luqmati` files include settings, exclusions, favorites, swaps, checkmarks and the diary. Files are not encrypted; choose a private storage location. Import validates data before asking to replace current data. Restored reminders stay off until enabled again.
- Adjustable approximate meal reminders, notification-permission controls and test notification. Milk guidance and feeding-safety references remain available.

## Install

Open the successful **Build Android APK** run in this repository's Actions tab. Download **Luqmati-Android-APK**, extract its ZIP and open `app-debug.apk` on your Android phone. Choose the real birth date, adjust the number of meals, enable reminders and grant notification permission.

This is a personal debug APK. Builds use an ephemeral debug signing key; installing a later build may require uninstalling the previous version. Uninstalling erases local data. From v1.2 onward, export a backup before uninstalling and restore it after installation. Versions before v1.2 do not have the in-app backup feature. A permanent private signing key is needed for reliable seamless updates.

## Development and validation

JDK 17, Gradle 8.9, Android SDK 35 and AGP 8.7.3. No Gradle wrapper is bundled.

- `gradle assembleDebug lintDebug`
- CI runs the original planner tests plus `tests/FeatureTest.java`: catalog consistency, daily coverage, allergen gates, exclusions, recipe overrides, Arabic backup round-trip and rejection of invalid backups.
- CI also runs `connectedDebugAndroidTest` on an API 35 emulator, launches the Arabic home, shopping, diary, food-preference, settings and guide screens, validates malformed diary rejection and captures screenshots in **Luqmati-UI-checks**.
- Phone-specific notification behavior, battery optimization, external file providers and installation upgrades still need checking on the user's device.

## Feeding guidance

These are general meal ideas, not a clinician-reviewed complete nutritional plan. Start according to readiness, preserve milk feeds, adjust texture and quantity to the child, and supervise every meal. Do not use ingredient photos as serving instructions. Introduce common allergens individually in small amounts, retain tolerated foods, and seek clinical advice for significant eczema, known allergy or concerning reactions. Excluding foods may reduce variety. Severe breathing difficulty, tongue swelling or collapse requires emergency medical help.

References reviewed 3 November 2026:

- https://www.who.int/health-topics/complementary-feeding
- https://www.nhs.uk/baby/weaning-and-feeding/babys-first-solid-foods/
- https://www.nhs.uk/best-start-in-life/baby/weaning/what-to-feed-your-baby/from-around-6-months/
- https://www.nhs.uk/baby/weaning-and-feeding/foods-to-avoid-giving-babies-and-young-children/

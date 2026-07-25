# Fix Data Class Definitions and Dummy Data Errors

The `DonationCampaign` and `News` data classes are currently defined without primary constructors, which prevents them from being instantiated with named parameters in `DummyData.kt`. Additionally, there is a typo in `DonationCampaign` where `collecAmount` is used instead of `collectedAmount`.

## Proposed Changes

### [Data Models]

#### [MODIFY] [DonationCampaign.kt](file:///C:/Users/felix/Downloads/DonationApp/app/src/main/java/com/example/donationapp/data/model/DonationCampaign.kt)
- Update the class definition to use a primary constructor.
- Rename `collecAmount` to `collectedAmount` to match its usage in `DummyData.kt`.

#### [MODIFY] [News.kt](file:///C:/Users/felix/Downloads/DonationApp/app/src/main/java/com/example/donationapp/data/model/News.kt)
- Update the class definition to use a primary constructor.

## Verification Plan

### Automated Tests
- Run `analyze_file` on `DummyData.kt`, `DonationCampaign.kt`, and `News.kt` to ensure no more syntax errors.
- Run a Gradle build (`app:assembleDebug`) to verify the project compiles correctly.

### Manual Verification
- None required as these are internal data model changes.

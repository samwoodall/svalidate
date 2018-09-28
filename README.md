# Svalidate

##### A simple EditText validation library written in kotlin for android



## Setup

 Gradle

```groovy
implementation 'com.samwoodall:svalidate:1.0.0'
```

Maven

```xml
<dependency>
  <groupId>com.samwoodall</groupId>
  <artifactId>svalidate</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```



## Start

Create a Validator and set the validation rules

```kotlin
val validator = Validator()
                .checkNotBlank()
                .checkAllAlphanumeric()
                .checkAtLeastOneUpper()
```



Then validate the input text of an EditText against those rules

```kotlin
button.setOnClickListener {
            val isSuccess = validator.validate(edit_text)
        }
```



Choose whether to automatically set an error on the EditText corresponding to the validation error

```kotlin
validator.validate(editText = edit_text, showError = false)
```



Set custom validation error messages or use the provided defaults

```kotlin
val validator = Validator()
                .checkNotBlank(errorMessage = R.string.svalidate_not_blank)
                .checkAllAlphanumeric(errorMessage = R.string.alphanumeric_error)
                .checkAtLeastOneUpper()
```




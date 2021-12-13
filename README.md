# Squishy

![animation](https://github.com/KothamireNarendra/Squishy/blob/main/Squishy.gif)

## Usage

Add `squishy()` modifier on your composable element.

```kotlin
  Text(
    text = "Squishhhhyyyyy",
    modifier = Modifier
    .size(200.dp, 100.dp)
    .squishy()
   )
```

### onClick lambda
Lambda will be called when user clicks the element. You can pass `onClick` if you want to perform any action on click.

```kotlin
Modifier.explodeOnClick(color = backColor,
          onClick = {
              // To some action
          })
```

# djl-kotlin

[Deep Java Library](https://djl.ai/) bindings and other support for Kotlin.

This project doesn't aspire to completely cover the DJL APIs. Rather, it exposes them, but selectively provides 
wrappers and extensions to give a safer and more idiomatic Kotlin experience. 

## Structure

djl-kotlin has a module for each DJL maven artifact. If you add a djl-kotlin module as a dependency, this
gets you the underlying DJL module plus wrappers and extensions.


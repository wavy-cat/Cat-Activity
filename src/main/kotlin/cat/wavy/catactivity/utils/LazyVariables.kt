package cat.wavy.catactivity.utils

class LazyVariables {
    private val providers = mutableMapOf<String, () -> String>()
    private val cache = mutableMapOf<String, String>()

    fun put(key: String, value: String) {
        cache[key] = value
    }

    fun putLazy(key: String, provider: () -> String) {
        providers[key] = provider
    }

    fun resolve(key: String): String? {
        cache[key]?.let { return it }
        providers[key]?.let {
            val value = it()
            cache[key] = value
            return value
        }
        return null
    }

    fun replaceIn(template: String): String {
        var result = template
        for (key in providers.keys + cache.keys) {
            if (key in result) {
                resolve(key)?.let { result = result.replace(key, it) }
            }
        }
        return result
    }
}

fun String.replaceVariables(variables: LazyVariables): String = variables.replaceIn(this)

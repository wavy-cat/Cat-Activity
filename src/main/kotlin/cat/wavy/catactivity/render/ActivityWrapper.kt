package cat.wavy.catactivity.render

/**
 * An activity wrapper
 *
 * The underlying SDK implementation is hidden, so that it is
 * convenient to replace the SDK implementation in the future
 *
 * @param state The state of the activity
 * @param details The details of the activity
 * @param startTimestamp The start timestamp of the activity
 * @param endTimestamp The end timestamp of the activity
 * @param largeImageKey The large image key of the activity
 * @param largeImageText The large image text of the activity
 * @param smallImageKey The small image key of the activity
 * @param smallImageText The small image text of the activity
 * @param buttonLabel The text displayed on the button
 * @param buttonLink Link that is clicked to
 */
data class ActivityWrapper(
    val state: String?,
    val details: String? = null,
    val startTimestamp: Long? = null,
    val endTimestamp: Long? = null,
    var largeImageKey: String? = null,
    var largeImageText: String? = null,
    var smallImageKey: String? = null,
    var smallImageText: String? = null,
    var buttonLabel: String? = null,
    var buttonLink: String? = null,
)
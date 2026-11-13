package com.Snap2UI.app.data

import com.google.gson.annotations.SerializedName

data class UIComponent(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("x") val x: Int,
    @SerializedName("y") val y: Int,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("confidence") val confidence: Float,
    @SerializedName("text") val text: String
)

data class DetectResponse(
    @SerializedName("status") val status: String,
    @SerializedName("components") val components: List<UIComponent>
)

data class GenerateXmlResponse(
    @SerializedName("status") val status: String,
    @SerializedName("xml_code") val xmlCode: String
)

data class GenerateComposeResponse(
    @SerializedName("status") val status: String,
    @SerializedName("compose_code") val composeCode: String
)

data class SimilarityResult(
    @SerializedName("ssim_score") val ssimScore: Float,
    @SerializedName("pixel_accuracy_percent") val pixelAccuracyPercent: Float,
    @SerializedName("layout_match_score") val layoutMatchScore: Float,
    @SerializedName("differences") val differences: List<DifferenceItem>,
    @SerializedName("evaluation") val evaluation: String
)

data class DifferenceItem(
    @SerializedName("component") val component: String,
    @SerializedName("difference") val difference: String,
    @SerializedName("status") val status: String
)

data class SimilarityResponse(
    @SerializedName("status") val status: String,
    @SerializedName("similarity") val similarity: SimilarityResult
)

data class PromptRequest(
    @SerializedName("app_type") val appType: String,
    @SerializedName("screen_type") val screenType: String,
    @SerializedName("design_style") val designStyle: String,
    @SerializedName("primary_color") val primaryColor: String,
    @SerializedName("secondary_color") val secondaryColor: String,
    @SerializedName("required_components") val requiredComponents: List<String>,
    @SerializedName("layout_preferences") val layoutPreferences: String
)

data class PromptResponse(
    @SerializedName("status") val status: String,
    @SerializedName("components") val components: List<UIComponent>,
    @SerializedName("xml_code") val xmlCode: String,
    @SerializedName("compose_code") val composeCode: String
)

data class ProjectItem(
    val id: String,
    val title: String,
    val date: String,
    val type: String,
    val thumbnailRes: String
)

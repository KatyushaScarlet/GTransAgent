# LM Studio

## What is LM Studio?
LM Studio is a desktop application for discovering, downloading, and running local large language models (such as Qwen, Gemma, Llama, etc.) on your own computer. It ships with a built-in server that exposes an **OpenAI-compatible** REST API, so it can be used as a drop-in local translation backend.

## LM Studio Installation and Usage
1. Download and install LM Studio from the official site: [https://lmstudio.ai/](https://lmstudio.ai/)
2. Search for and download a model (e.g. `qwen3-4b`, `gemma-3-4b-it`).
3. Open the **Developer / Local Server** tab and start the server. By default it listens on `http://127.0.0.1:1234`, and the chat completions endpoint is `http://127.0.0.1:1234/v1/chat/completions`.
4. Note the exact **model identifier** shown in LM Studio — it must match the `model` value in `LMStudio.yaml`.

## LMStudio.yaml Configuration Instructions

```yaml
# LM Studio OpenAI-compatible API address.
# Note: For non-local access, ensure devices are on the same LAN with firewall configured,
# or the device running LM Studio has a public IP, and enable "Serve on Local Network" in LM Studio.
url: http://127.0.0.1:1234/v1/chat/completions

# API key is optional for LM Studio. Leave blank for local servers,
# or set LMSTUDIO_API_KEY in the environment if your server requires one.
apiKey:

# List of all engines you want to use and display as selectable options to clients
engineMapping:
  # Each LM Studio model corresponds to one GTransAgent engine
  qwen3-4b: # Unique identifier for the engine within GTransAgent (customizable)
    name: Qwen 3 4B # Display name when selecting private engine in client (customizable)
    model: qwen3-4b # Model identifier as loaded in LM Studio (must match exactly)

concurrent: 3  # Number of concurrent requests to the LM Studio API within one translation request.
               # Requests beyond this number will be queued.

# Whether to enable thinking/reasoning mode for models that support it.
# LM Studio has no universal "think" flag. When false, the request sends
# chat_template_kwargs.enable_thinking=false, which works for Qwen3 / Qwen3.5.
# Models that don't recognize this kwarg simply ignore it.
enableThinking: false

# Whether to pass surrounding input items as context during translation.
# When enabled, previous and next items in the same batch are included as context
# to help the LLM maintain translation consistency.
enableContext: true

# System role prompts.
# Placeholders like {{srcLang}}, {{targetLang}}, {{glossarySensitive}} will be automatically replaced during runtime.
# Can be modified according to your needs
systemPrompts:

# User role prompts.
# Can be modified according to your needs
userPrompts:
```

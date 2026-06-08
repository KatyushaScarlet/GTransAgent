# LM Studio

## 什么是 LM Studio？
LM Studio 是一款桌面应用，用于在本地计算机上发现、下载并运行大型语言模型（如 Qwen、Gemma、Llama 等）。它内置了一个服务器，提供 **OpenAI 兼容** 的 REST API，因此可以作为本地翻译后端直接使用。

## LM Studio 的安装及使用
1. 从官网下载并安装 LM Studio：[https://lmstudio.ai/](https://lmstudio.ai/)
2. 搜索并下载一个模型（如 `qwen3-4b`、`gemma-3-4b-it`）。
3. 打开 **Developer / 本地服务器（Local Server）** 标签页并启动服务。默认监听 `http://127.0.0.1:1234`，对话补全接口为 `http://127.0.0.1:1234/v1/chat/completions`。
4. 记下 LM Studio 中显示的准确 **模型标识（model identifier）**，它必须与 `LMStudio.yaml` 中的 `model` 值完全一致。

## LMStudio.yaml 配置说明

```yaml

# LM Studio OpenAI 兼容 API 地址。
# 注意非本机访问需要确保在同一个局域网，并配置防火墙；或者 LM Studio 运行的设备具有公网 IP，
# 并在 LM Studio 中开启 "Serve on Local Network"（局域网内提供服务）。
url: http://127.0.0.1:1234/v1/chat/completions

# LM Studio 的 API Key 为可选项。本地服务器可留空，
# 或在服务器需要鉴权时通过环境变量 LMSTUDIO_API_KEY 设置。
apiKey:

# 所有你希望使用，并展示到客户端的可选择引擎列表
engineMapping:
  # 一个 LM Studio 模型对应一个 GTransAgent 引擎
  qwen3-4b: # 引擎在 GTransAgent 内部的唯一标识，自定义
    name: Qwen 3 4B # 用于在客户端选择私有引擎时显示的名称，自定义
    model: qwen3-4b # 你要使用的 LM Studio 模型标识（必须与已加载模型完全一致）

concurrent: 3  # 一次翻译请求内调用 LM Studio API 的并发请求数，超过这个数量将需要排队处理

# 是否为支持的模型启用思考/推理模式。
# LM Studio 没有统一的 "think" 开关。当设为 false 时，请求会发送
# chat_template_kwargs.enable_thinking=false，该方式对 Qwen3 / Qwen3.5 有效。
# 不识别该字段的模型会自动忽略它。
enableThinking: false

# 是否在翻译时传入相邻输入项作为上下文。
# 启用后，同一批次中的前后条目会作为上下文一并传入，
# 以帮助大模型保持翻译一致性。
enableContext: true

# 系统角色提示词。
# {{srcLang}}，{{targetLang}}，{{glossarySensitive}} 等为占位符，在运行过程中会被自动替换。
# 可以根据你的需要修改
systemPrompts:

# 用户角色提示词。
# 可以根据你的需要修改
userPrompts:

```

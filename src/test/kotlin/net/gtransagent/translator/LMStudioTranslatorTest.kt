package net.gtransagent.translator

import net.gtransagent.translator.base.ITranslator


class LMStudioTranslatorTest : TranslatorTest() {
    override fun getTranslatorCode(): String {
        return LMStudioTranslator.NAME
    }

    override fun getITranslator(): ITranslator {
        return LMStudioTranslator()
    }

    override fun getTranslationEngines(): List<String> {
        return listOf("qwen3-4b", "gemma-3-4b-it")
    }
}

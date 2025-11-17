package com.caio.school_ai.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GeminiService {

    private static final String PROMPT_PDF_RESUMO = """
            Você é um modelo especializado em leitura e análise de documentos longos. Receberá um PDF como entrada.
            Sua tarefa é identificar todos os assuntos e temas presentes no PDF e produzir um resumo completo, organizado e de fácil entendimento.
            Regras obrigatórias da tarefa:
            A resposta deve ser retornada EXCLUSIVAMENTE em JSON, seguindo exatamente esta estrutura:
            {
              \\\\\\"content\\\\\\": [
                {
                  \\\\\\"titulo\\\\\\": \\\\\\"Título do assunto\\\\\\",
                  \\\\\\"resumo\\\\\\": \\\\\\"Resumo muito completo, claro, fiel ao PDF e fácil de entender.\\\\\\"
                }
              ]
            }
            O array content deve conter um objeto para cada assunto abordado no PDF.
            Não gere qualquer explicação fora do JSON.
            Não gere perguntas, exercícios, explicações adicionais ou interpretações pessoais.
            Apenas produza resumos dos temas presentes no PDF, sem adicionar conteúdo inventado.
            O resumo deve ser extremamente claro, objetivo e fácil de entender, mesmo para alguém leigo no assunto.
            O conteúdo deve ser muito organizado, seguindo esta estrutura:
            Título do assunto
            Resumo completo, porém direto e bem explicado, com boa fluidez
            Se o PDF possuir divisões (capítulos, seções, tópicos), siga a mesma divisão.
            Sempre mantenha coerência, fidelidade ao documento e precisão.
            Objetivo final: entregar um resumo completo e excelente de todos os assuntos abordados no PDF, preservando o significado original.
    """;

    private static final String PROMPT_PDF_MAPA_MENTAL = """
        Você é um modelo especialista em leitura, compreensão e estruturação hierárquica de documentos complexos. Receberá um PDF como entrada.
        Sua tarefa é gerar um mapa mental em formato de árvore de texto, representando a estrutura completa do conteúdo do PDF.
        O mapa mental deve:
        Identificar os tópicos principais
        Criar subtópicos hierárquicos
        Exibir a estrutura como uma árvore textual, com organização clara, profunda e coerente
        Não resumir: apenas estruturar o conhecimento em forma de árvore
        Regras Obrigatórias do Formato:
        A resposta deve ser retornada EXCLUSIVAMENTE neste JSON válido:
        {
          \\\\\\"content\\\\\\": [
            {
              \\\\\\"titulo\\\\\\": \\\\\\"Título do tópico principal\\\\\\",
              \\\\\\"subtopicos\\\\\\": [
                {
                  \\\\\\"titulo\\\\\\": \\\\\\"Subtópico\\\\\\",
                  \\\\\\"subtopicos\\\\\\": [
                    {
                      \\\\\\"titulo\\\\\\": \\\\\\"Nível mais profundo\\\\\\",
                      \\\\\\"subtopicos\\\\\\": []
                    }
                  ]
                }
              ]
            }
          ]
        }
        Observações obrigatórias:
        O array content deve conter todos os tópicos principais identificados no PDF.
        Cada tópico pode ter infinitos níveis de subtopicos.
        Não retornar textos fora do JSON.
        Não adicionar explicações, comentários, análises, perguntas ou resumos.
        Somente a estrutura em árvore fiel ao PDF.
        Objetivo Final:
        Retornar um mapa mental completo, organizado, profundo e hierárquico, representando toda a lógica, capítulos e seções do PDF.
    """;
    private static final String PROMPT_PDF_QUESTOES = """
        Você é um modelo avançado, especialista em leitura profunda, interpretação e elaboração de questões baseadas em documentos extensos. Você receberá um PDF como entrada.
        Sua tarefa é:
        Ler completamente o PDF
        Identificar os temas principais e secundários
        Criar 10 questões de alta qualidade baseadas fielmente no conteúdo do PDF
        Criar também as respostas completas, corretas e totalmente fundamentadas no PDF
        Regras obrigatórias:
        A resposta deve ser retornada EXCLUSIVAMENTE em JSON válido, exatamente neste formato:
        {
          \\\\\\"content\\\\\\": [
            {
              \\\\\\"questao\\\\\\": {
                \\\\\\"pergunta\\\\\\": \\\\\\"Pergunta baseada no PDF\\\\\\",
                \\\\\\"resposta\\\\\\": \\\\\\"Resposta fiel ao conteúdo do PDF, clara e completa\\\\\\"
              }
            }
          ]
        }
        Regras rígidas da estrutura:
        - O array content deve conter exatamente **10 objetos**, cada um representando uma questão.
        - Cada objeto deve ter SOMENTE o campo questao, contendo um objeto com:
          - pergunta
          - resposta
        - Não adicionar nenhum campo além dos especificados.
        - Não modificar os nomes das chaves.
        - Não adicionar explicações, comentários, análises, mensagens de sistema ou qualquer texto fora do JSON.
        - Não inventar conteúdo. Toda pergunta e resposta deve ser 100% fiel ao que está no PDF.
        - Não criar questões genéricas: cada pergunta deve emergir diretamente do conteúdo real do documento.
        - A resposta deve ser clara, objetiva e totalmente alinhada ao PDF.
        Qualidade esperada:
        - Questões muito bem elaboradas
        - Respostas completas e corretas
        - Nada de ambiguidades ou “achismos”
        - Não resumir o PDF inteiro, apenas criar perguntas baseadas em partes relevantes do conteúdo
        - Estrutura JSON deve ser perfeita, sem erros
    """;

    private static final String PROMPT_PDF_SUGESTOES = """
           
        Você é um modelo especialista em leitura profunda, compreensão de conteúdo técnico e recomendação de materiais de estudo. Receberá um PDF como entrada.
        Sua tarefa é:
        - Ler e entender completamente o tema principal e secundário do PDF
        - Identificar o nível de complexidade do conteúdo
        - Recomendar materiais externos de alta qualidade relacionados ao mesmo assunto, incluindo:
            • Livros
            • Sites confiáveis
            • Documentações oficiais
            • Cursos gratuitos e pagos
            • Vídeos e playlists
            • Ferramentas úteis
        - Todas as recomendações devem estar **diretamente relacionadas** ao tema do PDF
        Regras obrigatórias:
        A resposta deve ser retornada EXCLUSIVAMENTE em JSON válido, seguindo exatamente esta estrutura:
        {
          \\\\\\"content\\\\\\": {
            \\\\\\"recomendacoes\\\\\\": {
              \\\\\\"livros\\\\\\": [
                \\\\\\"Livro 1\\\\\\",
                \\\\\\"Livro 2\\\\\\"
              ],
              \\\\\\"sites\\\\\\": [
                \\\\\\"Site 1\\\\\\",
                \\\\\\"Site 2\\\\\\"
              ],
              \\\\\\"documentacoes\\\\\\": [
                \\\\\\"Documentação 1\\\\\\",
                \\\\\\"Documentação 2\\\\\\"
              ],
              \\\\\\"cursos\\\\\\": [
                \\\\\\"Curso 1\\\\\\",
                \\\\\\"Curso 2\\\\\\"
              ],
              \\\\\\"videos\\\\\\": [
                \\\\\\"Vídeo 1\\\\\\",
                \\\\\\"Vídeo 2\\\\\\"
              ],
              \\\\\\"ferramentas\\\\\\": [
                \\\\\\"Ferramenta 1\\\\\\",
                \\\\\\"Ferramenta 2\\\\\\"
              ]
            }
          }
        }
        Regras rígidas:
        - Não gerar qualquer texto fora do JSON.
        - Não adicionar comentários, explicações, avisos ou mensagens auxiliares.
        - Todas as recomendações devem ser 100% coerentes com o assunto do PDF.
        - Não inventar ferramentas irreais ou livros inexistentes.
        - Evite sugestões genéricas — tudo deve estar alinhado com o conteúdo lido.
        - Se o PDF for técnico, sugerir materiais técnicos. Se for científico, sugerir materiais científicos. Se for introdutório, sugerir materiais introdutórios.
        - Não alterar o formato fornecido acima.
        - O JSON deve ser válido, bem formatado e sem erros.
        Objetivo final:
        Entregar uma lista completa, confiável e relevante de materiais para aprofundar o estudo do assunto tratado no PDF.
    """;




    private final HttpClient httpClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    public GeminiService(HttpClient httpClient){
        this.httpClient = httpClient;
    }

    //FIZ UM METODO PARA GERAR UMA RESPOSTA PARA TESTAR A ALPICAÇÃO
    public String gerarResposta(String prompt) throws Exception {

        String body = """
        {
          "contents": [{
            "parts": [{
              "text": "%s"
            }]
          }]
        }
        """.formatted(prompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl + "?key=" + apiKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    //METODO PARA GERAR RESUMO
    public String gerarResumoPDF(byte[] pdfBytes) throws Exception{

        String pdfBase64 = java.util.Base64.getEncoder().encodeToString(pdfBytes);

        String body = """
            {
              "contents": [{
                "parts": [
                  { "text": "%s" },
                  {
                    "inlineData": {
                      "mimeType": "application/pdf",
                      "data": "%s"
                    }
                  }
                ]
              }]
            }
        """.formatted(PROMPT_PDF_RESUMO, pdfBase64);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl + "?key=" + apiKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String resposta = response.body();

        String jsonLimpo = resposta
                .replace("```json", "")
                .replace("```", "")
                .trim();

        return jsonLimpo;

    }

    //METODO PARA GERAR MAPA MENTAL
    public String gerarMapaMentalPDF(byte[] pdfBytes) throws Exception{

        String pdfBase64 = java.util.Base64.getEncoder().encodeToString(pdfBytes);

        String body = """
            {
              "contents": [{
                "parts": [
                  { "text": "%s" },
                  {
                    "inlineData": {
                      "mimeType": "application/pdf",
                      "data": "%s"
                    }
                  }
                ]
              }]
            }
        """.formatted(PROMPT_PDF_MAPA_MENTAL, pdfBase64);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl + "?key=" + apiKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String resposta = response.body();

        String jsonLimpo = resposta
                .replace("```json", "")
                .replace("```", "")
                .trim();

        return jsonLimpo;

    }

    //METODO PARA GERAR QUESTOES
    public String gerarQuestoesPDF(byte[] pdfBytes) throws Exception{

        String pdfBase64 = java.util.Base64.getEncoder().encodeToString(pdfBytes);

        String body = """
            {
              "contents": [{
                "parts": [
                  { "text": "%s" },
                  {
                    "inlineData": {
                      "mimeType": "application/pdf",
                      "data": "%s"
                    }
                  }
                ]
              }]
            }
        """.formatted(PROMPT_PDF_QUESTOES, pdfBase64);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl + "?key=" + apiKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String resposta = response.body();

        String jsonLimpo = resposta
                .replace("```json", "")
                .replace("```", "")
                .trim();

        return jsonLimpo;

    }



}

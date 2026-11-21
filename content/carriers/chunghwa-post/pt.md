---
title: Seguimento Chunghwa Post: código TW, estados e alfândega
description: Seguir uma encomenda Chunghwa Post de ou para Taiwan: formato do número em TW, significado dos estados em chinês, entrega pelos CTT e alfândega.
slug: chunghwa-post-tracking
published: 2026-11-21
updated: 2026-11-21
---

A Chunghwa Post (中華郵政, Chunghwa Post Co., Ltd.) é o operador postal nacional de Taiwan, uma empresa pública detida pelo Ministério dos Transportes e Comunicações que também gere poupança postal e seguros de vida. Podes seguir um envio no seu [Track & Trace Service](https://postserv.post.gov.tw/pstmail/main_mail.html?targetTxn=EB500201), em inglês ou chinês, ou colar o código diretamente na caixa acima. O número consta do talão entregue ao remetente no balcão: numa encomenda vinda de Taiwan, pede-o ao vendedor ou à loja. O portal oficial exige um código de imagem (Captcha) e só mostra objetos expedidos nos últimos seis meses.

## Número de seguimento Chunghwa Post: formato e exemplos

Um objeto internacional expedido de Taiwan tem 13 caracteres: duas letras, nove algarismos (o último é um dígito de controlo) e `TW`. As notas do portal da Chunghwa Post indicam o que significa a primeira letra. Exemplos com algarismos inventados:

| Exemplo (inventado) | Tipo de envio |
| --- | --- |
| `EE482910576TW` | `E`: EMS, serviço expresso internacional (ou `EE`/`EZ` e `FT`/`FZ`, como `FT482910576TW`, para a China continental). |
| `RR482910576TW` | `R`: correio registado internacional. |
| `LX482910576TW` | `L`: ePacket internacional (pequeno pacote rastreável até 2 kg, entregue na caixa de correio sem assinatura). |
| `CP482910576TW` | `C`: encomenda postal internacional. |
| `48291057364918` | 14 algarismos (ou 20): envio doméstico dentro de Taiwan, consultado no separador nacional da Chunghwa Post. |

A Chunghwa Post avisa que os pequenos pacotes ordinários começados pela letra `U` (como `UH482910576TW`) não têm seguimento online. Se ainda não tens a referência, vê [como encontrar o número de seguimento](guide:find-tracking-number).

## Estados da Chunghwa Post: o que significam as mensagens em chinês

Estes são os registos em chinês tradicional da Chunghwa Post que o Peek reconhece ao longo do percurso:

| A Chunghwa Post mostra | O que significa |
| --- | --- |
| `交寄郵件` | Entregue ao balcão de uma estação de correios («Aceite pela transportadora»). |
| `運輸途中` | Em circulação entre centros da Chunghwa Post («Em trânsito»). |
| `離開進口局` | Saiu de uma estação postal durante o encaminhamento. |
| `到達出口互換局` | Chegou à estação de permuta de exportação que o expede para o estrangeiro. |
| `已出口－資料詳如右方` | Exportado: já saiu de Taiwan. |
| `郵袋接收` | A mala postal onde segue o objeto foi recebida. |
| `到達寄達國互換局` | Chegou à estação de permuta do país de destino. |
| `海關查驗` | Apresentado para controlo aduaneiro («Na alfândega»). |
| `離開寄達國互換局` | Saiu da estação de permuta do país de destino. |
| `到達寄達國投遞局` | Chegou ao centro de distribuição do país de destino. |
| `安排投遞中` | Na estação distribuidora, a aguardar saída para giro. |
| `郵件投遞中` | Saiu para entrega («Em distribuição»). |
| `投遞不成功` | Entrega não conseguida («Tentativa de entrega»). O motivo ou eventual devolução vêm numa coluna de observações que o Peek não importa: consulta o portal da Chunghwa Post. |
| `投遞成功` | Entregue («Entregue»). |

`安排投遞中` e `郵件投遞中` são etapas de entrega da própria Chunghwa Post para envios que entram em Taiwan. Numa encomenda vinda de Taiwan para Portugal ou para o Brasil, os últimos passos vêm do operador postal de destino.

Se o portal responder `查無此號碼資料！！` (sem dados para esse número), confirma os 13 caracteres e verifica se o envio foi feito há menos de seis meses.

## Encomendas Chunghwa Post para Portugal e Brasil: entrega e alfândega

A Chunghwa Post transporta o objeto até à saída de Taiwan; depois, o correio nacional do país de destino assume o percurso com o mesmo código `TW`:

- **Portugal**: a tabela de atalhos para correios estrangeiros da Chunghwa Post não inclui ligação direta para Portugal, mas o operador postal designado é os [CTT](carrier:ctt), onde podes pesquisar o mesmo número `TW` assim que o envio chega ao país (Portugal também não consta da lista de treze países que deixaram de rastrear cartas registadas internacionais).
- **Brasil**: a tabela da Chunghwa Post remete para os Correios (`correios.com.br`) em EMS e encomendas internacionais, mas assinala sem seguimento as cartas registadas — o Brasil faz parte dos treze países que, segundo a Chunghwa Post, deixaram de fornecer rastreamento online para correio registado internacional (`R…TW`).

Avisos de levantamento e prazos em loja postal seguem as regras dos [CTT](carrier:ctt) (ou dos Correios, no Brasil); consulta [entrega falhada](guide:missed-delivery).

**Alfândega em Portugal.** Taiwan está fora da União Europeia, pelo que todas as encomendas com mercadorias passam pelo desalfandegamento. Em **Portugal** (continente, Açores e Madeira), aplica-se o IVA de importação desde o primeiro euro (salvo cobrança prévia no checkout via IOSS). Desde 1 de julho de 2026, as compras online extracomunitárias até 150 € pagam também um direito aduaneiro da UE de 3 € por categoria de artigo; desde 1 de novembro de 2026 acresce uma taxa de manuseamento da União de 2 € por artigo, independentemente do valor da encomenda, além do serviço de apresentação à alfândega cobrado pelo operador postal (no Brasil aplicam-se as regras tributárias brasileiras de importação postal). Mais pormenores em [encomendas retidas na alfândega](guide:customs).

## Enviar uma encomenda para Taiwan: entrega e regras aduaneiras

O correio do teu país encaminha o objeto até Taiwan e a Chunghwa Post faz a entrega final. Segundo a Chunghwa Post, todas as encomendas importadas são inspecionadas pela alfândega taiwanesa, a quem cabe decidir se há lugar a imposto. A Alfândega de Taipé (Taipei Customs) indica três escalões para encomendas postais:

- **Até 2.000 NT$** (valor CIF: mercadoria, seguro e transporte): isento de direitos e impostos.
- **De 2.000 NT$ a 5.000 US$**: a alfândega encarrega habitualmente os correios de cobrar os direitos no ato da entrega mediante um *Duty Memo for Foreign Imports by Parcel Post*.
- **Acima de 5.000 US$** (valor FOB): o destinatário trata do desalfandegamento e paga diretamente na estância aduaneira.

A Alfândega de Taipé alerta ainda para bens proibidos ou sujeitos a licença na importação, como dispositivos médicos (incluindo produtos com ácido hialurónico ou aparelhos estéticos), sementes, produtos com carne, tabaco aquecido, cigarros eletrónicos e contrafações.

## Seguimento Chunghwa Post parado, atrasado ou extraviado

**Sem atualizações depois de `已出口－資料詳如右方`.** O objeto já saiu de Taiwan e está em trânsito aéreo ou a aguardar receção e controlo aduaneiro no destino. Procura o mesmo número `TW` nos [CTT](carrier:ctt) (ou nos Correios, no Brasil) e vê [porque é que o seguimento não atualiza](guide:tracking-not-updating).

**Encomenda atrasada ou perdida.** Apenas o remetente pode apresentar um pedido escrito de averiguação na estação de correios de Taiwan onde fez o envio, acompanhado do recibo original: no prazo de **4 meses** a contar do dia seguinte à expedição para EMS e de **6 meses** para encomendas e correio registado. Fora desse prazo, a Chunghwa Post pode recusar a averiguação e não paga indemnização. Pede ao vendedor que o apresente a tempo e consulta [encomenda perdida](guide:lost-parcel).

**Dada como entregue mas não recebida.** Em Portugal ou no Brasil, a entrega final cabe ao correio local: verifica o comprovativo de entrega desse operador e consulta [entregue mas não recebida](guide:delivered-not-received).

## Contactos e apoio ao cliente da Chunghwa Post

Os horários abaixo estão na hora de Taiwan (`UTC+8`, sem hora de verão):

- **Linha de apoio 24 horas**: `0800-700-365` (gratuita a partir de rede fixa em Taiwan; prime `3` para atendimento em inglês). A partir de telemóvel ou do estrangeiro liga para o número pago `(04) 2354-2030`, ou seja, `+886 4 2354 2030` retirando o `0` inicial do indicativo.
- **Objetos a entrar em Taiwan**: `(03) 3180-700`, extensão `2431` (EMS) e `2432` (encomendas) de segunda a sexta-feira, exceto feriados, das 8:00 às 12:00 e das 13:30 às 16:30; extensão `2430` (registados por avião) das 8:00 às 12:00 e das 13:00 às 16:00.
- **Encomendas aéreas a sair de Taiwan**: `(03) 383-3776`, extensão `271` ou `272`, de segunda a sexta-feira, exceto feriados, das 8:00 às 16:30.
- **Online**: formulário *Opinion Box* na página [Contact Us](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=35090601), com resposta até 7 dias (indica o número de registo e o nome e morada do remetente e do destinatário, confirmando depois o link recebido por e-mail).

## Seguir uma encomenda Chunghwa Post com o Peek

O [Peek](/) reconhece de imediato como Chunghwa Post um número de duas letras, nove algarismos e `TW` quando a primeira letra é `C`, `E`, `L` ou `R` (ou o prefixo é `FT` ou `FZ`) e o dígito de controlo está correto; se falhar, confirma o código no talão. Links `CSController?MAILNO=` também funcionam. Um código `UH…TW` é classificado pela forma como correio internacional (podes escolher a Chunghwa Post manualmente em «Alterar» ou «Alterar transportadora»), enquanto outros códigos `U…TW` e números domésticos de 14 ou 20 algarismos são recusados pelo adaptador da Chunghwa Post («não emite números como este»).

O Peek lê o seguimento internacional da Chunghwa Post e mostra cada leitura em chinês tal como publicada, com a estação responsável (convertida para a respetiva cidade quando o Peek a consegue localizar). As leituras feitas por estações de Taiwan são lidas em `Asia/Taipei` e convertidas para o teu fuso («Horas no teu fuso horário: {{zone}}.»); as leituras no estrangeiro mantêm a hora local desse correio. A Chunghwa Post não indica data prevista de entrega nem peso, e o Peek ignora a coluna de observações.

Num número terminado em `TW`, o Peek consulta também o correio nacional do país a partir do qual segues o objeto — [CTT](carrier:ctt) a partir de Portugal, ou Correios a partir do Brasil — mudando para esse operador assim que apresenta leituras recentes e mantendo o histórico de Taiwan. No sentido inverso, quando o correio de origem indica Taiwan como destino, o Peek interroga a Chunghwa Post com o mesmo código postal, exceto se começar por `U`.

Enquanto tens a encomenda aberta no ecrã, ou no dia da entrega com as notificações ativas, o Peek verifica o seguimento direto até a cada 10 minutos, e até a cada 2 minutos quando entra «Em distribuição» (nesse dia a partir das 6h locais enquanto o portal da Chunghwa Post responder diretamente); fora disso, e após dois dias sem novidades (12 horas enquanto estiver em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Através dos serviços de seguimento, verifica até a cada 15 minutos nesse dia «Em distribuição» nas mesmas condições, caso contrário de hora a hora, ou a cada 3 horas após um dia sem atualizações. Quando o objeto passa para o correio de destino, o Peek segue a cadência desse operador. Podes ativar alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega». O Peek não pode apresentar reclamações junto da Chunghwa Post nem dos CTT por ti.

## Perguntas frequentes sobre o seguimento Chunghwa Post

### Chunghwa Post e Taiwan Post são a mesma transportadora?

Sim. A Chunghwa Post (中華郵政) é o correio oficial de Taiwan, e o Peek reconhece-a pelos dois nomes. Os seus códigos internacionais terminam em `TW`.

### Como seguir uma encomenda Chunghwa Post em Portugal?

No portal da Chunghwa Post até surgir `已出口－資料詳如右方` e, depois da chegada ao país, com o mesmo código `TW` nos [CTT](carrier:ctt) (mesmo não havendo atalho direto para Portugal na tabela da Chunghwa Post). Se seguires a encomenda a partir de Portugal, o Peek consulta os CTT automaticamente.

### O que significa `海關查驗`?

Que o objeto está sob verificação aduaneira («Na alfândega»). Vê [encomendas retidas na alfândega](guide:customs) para saberes como se aplicam em Portugal o IVA, o direito de 3 € (encomendas até 150 €) e a taxa de manuseamento de 2 € por artigo.

### Durante quanto tempo fica disponível o seguimento na Chunghwa Post?

O portal online guarda apenas envios dos últimos seis meses. Para pedir uma averiguação escrita em Taiwan com o talão original, o remetente dispõe de 4 meses em EMS e de 6 meses em encomendas e correio registado a contar do dia seguinte à expedição.

:::sources
- [Chunghwa Post: Company Profile](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=1637282383451) – empresa postal pública nacional, correio, poupança e seguros de vida
- [Chunghwa Post: Tracking Service](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=351402) – acesso ao seguimento de EMS, cartas e encomendas
- [Chunghwa Post: Track & Trace Service](https://postserv.post.gov.tw/pstmail/main_mail.html?targetTxn=EB500201) – Captcha, seis meses de histórico, prefixos E/R/L/C/U, registados sem seguimento (incluindo Brasil), prazos de averiguação, tabela de correios de destino (Portugal sem link) e códigos domésticos
- [Chunghwa Post: 國際e小包 (ePacket)](https://www.post.gov.tw/post/internet/Postal/index.jsp?ID=1499060698896) – limite de 2 kg, entrega sem assinatura nem averiguação escrita
- [Chunghwa Post: 國際郵件查詢電話](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=160101) – telefones, extensões e horários para correio internacional
- [Chunghwa Post: English home page](https://www.post.gov.tw/post/internet/U_english2/) – linha 0800-700-365, número pago para telemóvel e formato +886
- [Chunghwa Post: 客服專線語音操作說明](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=160103) – atendimento 24 horas e tecla 3 para inglês
- [Chunghwa Post: Contact Us](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=35090601) – formulário Opinion Box, resposta em 7 dias e dados pedidos
- [Chunghwa Post: 國際包裹 FAQ](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=16020307&defaultAllOpen=1&sn=24FA12A9-11A6-4365-A8F5-C154C379AF02) – inspeção aduaneira de todas as encomendas importadas em Taiwan
- [Chunghwa Post: 郵件查詢的期限 FAQ](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=16020315&defaultAllOpen=1&sn=44431DCF-5197-43D5-95B6-5EFE13F5D67B) – recusa de averiguação e indemnização após o prazo
- [Taipei Customs: International Parcel](https://web.customs.gov.tw/etaipei/singlehtml/1342) – limites de 2.000 NT$ e 5.000 US$, Duty Memo
- [Taipei Customs: Cross-border online purchases](https://web.customs.gov.tw/etaipei/singlehtml/1373?cntId=1a74ff69fdd0483085a6a8c9eda54b07) – bens restritos ou proibidos na importação
- [Your Europe: Expedição e entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – direito aduaneiro de 3 € por artigo em encomendas até 150 €
- [Comissão Europeia: Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – aplicação do direito de 3 € desde 1 de julho de 2026
- [Comissão Europeia: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa de manuseamento de 2 € por artigo desde 1 de novembro de 2026
- [Comissão Europeia: Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – enquadramento aduaneiro e de IVA de Portugal
- [Universal Parcel Scraper: Chunghwa Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chunghwa-post) – deteção de códigos TW, estados em chinês e transição para o correio de destino
:::

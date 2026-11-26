---
title: Rastreamento GOFO Espanha: códigos GFES e CIES e suporte
description: Rastreie encomendas GOFO Spain (GOFO Express): códigos GFES e CIES, status em espanhol, 3 tentativas, guarda por 2 semanas, fotos de entrega e suporte.
slug: gofo-spain-tracking
published: 2026-11-26
updated: 2026-11-26
---

A GOFO Spain é a operação espanhola da GOFO, provedora de logística de última milha fundada nos Estados Unidos em agosto de 2023 que atua em parceria com entregadores nos Estados Unidos, Canadá, França, Países Baixos, Itália e Espanha. Na Espanha, a GOFO realiza entregas para marcas de e-commerce, varejistas e operadores logísticos; após três tentativas sem sucesso, a encomenda volta ao centro de distribuição e permanece guardada por duas semanas. Você pode consultar um envio na [página de rastreamento da GOFO Spain](https://www.gofo.com/es/tracking-results/) ou colar seu código na caixa acima.

## Códigos de rastreamento GOFO Spain: formatos GFES e CIES

Procure o código no e-mail de envio da loja online ou na página do pedido no marketplace. Os códigos da GOFO Spain começam com `GFES` ou `CIES` seguidos por 13 ou 14 dígitos:

| Formato (fictício) | O que é |
| --- | --- |
| `GFES26482917364052` | Código padrão GOFO Spain de 18 caracteres (`GFES` + 14 dígitos com dígito verificador; também ocorre `GFES` + 13 dígitos, com 17 caracteres). |
| `CIES4829173640582` | Formato alternativo de 17 ou 18 caracteres da GOFO Spain (`CIES` + 13 ou 14 dígitos). |

Para outros formatos de transportadoras europeias, veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da GOFO Spain

O feed de rastreamento da GOFO Spain usa códigos numéricos combinados com descrições em espanhol no horário local da Espanha (`Europe/Madrid`):

| A GOFO Spain informa (código) | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Pedido recibido` (`100`) | O vendedor registrou o pedido eletronicamente; a GOFO aguarda receber a encomenda física. | "Etiqueta criada" |
| `Llegada al centro de clasificación` (`202`), `Salida del centro de clasificación` (`200`), `Llegada a las instalaciones de destino de GOFO` (`201`), `En preparación para reparto` (`203`) | Recebida no centro de triagem da GOFO, despachada à unidade de destino e preparada para entrega local. | "Em trânsito" |
| `El paquete está en reparto` (`208`) | Saiu com um entregador da GOFO para entrega em domicílio na Espanha. | "Saiu para entrega" |
| `Entrega fallida` (`206`) | Tentativa de entrega sem sucesso (por exemplo, destinatário ausente ou problema no endereço). | "Tentativa de entrega" |
| `Devuelto al centro de clasificación` (`204`) | Devolvida ao centro de triagem/distribuição da GOFO após tentativa falha — ainda **não** foi devolvida ao remetente. | "Precisa de atenção" |
| `Devuelto` (`257` / `264`) | A encomenda está sendo devolvida ao remetente. | "Devolvida ao remetente" |
| `Entregado por En persona` / `Entregado por En el buzón` (`205`) | Entregue pessoalmente ou depositada na sua caixa de correio. | "Entregue" |

**Nota sobre `Devuelto al centro de clasificación` (`204`).** Ver *"Devuelto"* aqui pode assustar, mas o código `204` significa que o motorista levou o pacote de volta ao centro local de triagem da GOFO após uma visita sem sucesso, enquanto `Devuelto` (`257`/`264`) indica devolução efetiva ao remetente.

## Tentativas de entrega, guarda por 2 semanas e alfândega

De acordo com [gofo.com/es/en/](https://www.gofo.com/es/en/) e as [Perguntas Frequentes de Suporte da GOFO Spain](https://www.gofo.com/es/en/contact-us/):

- **Três tentativas e guarda por duas semanas:** a GOFO Spain afirma expressamente que, após **três tentativas de entrega sem sucesso**, as encomendas retornam ao centro de distribuição e ficam **armazenadas em segurança por duas semanas**. Entre em contato antes que esse prazo termine caso precise coordenar a entrega ou corrigir um problema de endereço.
- **Fotos de Comprovante de Entrega (POD):** para envios entregues, o [portal principal da GOFO Spain](https://www.gofo.com/es/) disponibiliza detalhes de POD que geralmente incluem uma foto do local onde o pacote foi deixado e uma descrição da entrega.
- **Pedidos de e-commerce fora da UE e alfândega:** muitas entregas da GOFO Spain vêm de lojas fora da União Europeia. Pela [Reforma Aduaneira da UE da Comissão Europeia](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en), pedidos de fora da UE de até €150 pagam €3 de imposto aduaneiro por item, e todo pedido desse tipo paga a taxa de manuseio da União de €2 por item, salvo cobrança prévia no checkout da loja. Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda GOFO Spain sumida, danificada ou marcada como entregue

O [FAQ de Atendimento da GOFO Spain](https://www.gofo.com/es/en/contact-us/) traz passos específicos conforme a situação:

- **Marcada como `Entregado` ("Entregue"), mas não recebida:** primeiro confira a foto de Comprovante de Entrega (POD) em [gofo.com/es/](https://www.gofo.com/es/). Verifique sua caixa de correio, áreas comuns de encomendas, porta de entrada e locais seguros próximos, além de perguntar a vizinhos, familiares ou portaria. Se continuar desaparecida, escreva prontamente para `cs@es.gofo.com` — anexar uma foto da entrada ou fachada do imóvel agiliza a investigação (embora a GOFO analise relatos mesmo sem foto).
- **Itens faltando em embalagem intacta ou extravio confirmado:** a GOFO orienta destinatários a **contatar diretamente o remetente** para tratar de itens faltantes ou pedidos de indenização por perda.
- Para listas de verificação gerais, veja [por que o rastreamento para de atualizar](guide:tracking-not-updating), [entregue mas não recebido](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da GOFO Spain

Contatos oficiais publicados em [Suporte ao Cliente GOFO Spain](https://www.gofo.com/es/en/contact-us/) e [Contacto](https://www.gofo.com/es/contact-us/):

- **E-mail de suporte:** `cs@es.gofo.com`.
- **Linha telefônica para destinatários:** listada como *Em breve* (*Coming soon* / *Próximamente*), com horário previsto de `09:00 – 18:00`, de segunda a sexta-feira, exceto feriados. Enquanto não abre, o e-mail é o canal oficial publicado para destinatários.

> Ignore mensagens não solicitadas por SMS ou WhatsApp exigindo pagamento online com cartão para "reentregar" ou "confirmar seu endereço" de uma encomenda GOFO. Verifique seu código `GFES` ou `CIES` diretamente em [gofo.com/es/tracking-results/](https://www.gofo.com/es/tracking-results/) ou escreva para `cs@es.gofo.com`. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Rastreie uma encomenda GOFO Spain com o Peek

O [Peek](/) reconhece automaticamente com alta confiança `GFES` + 13 ou 14 dígitos (`GFES26482917364052`, dígitos fictícios que passam no dígito verificador GOFO Europe) e `CIES` + 13 ou 14 dígitos (`CIES4829173640582`) como GOFO Spain, além de links colados de `gofo.com/es/...?id=`. Se precisar escolher a GOFO Spain manualmente, toque em "Alterar" ao adicionar ou em "Alterar transportadora" na tela do pacote (buscar "GOFO ES" também encontra).

O Peek lê diretamente o rastreador da GOFO Spain e mostra a etapa ("Etiqueta criada", "Em trânsito", "Saiu para entrega", "Tentativa de entrega", "Precisa de atenção", "Devolvida ao remetente", "Entregue"), o texto em espanhol com o local, o horário de entrega e o dia estimado quando a GOFO o fornece. Os códigos de operação definem a etapa; o Peek não exibe esses códigos nem o nome do serviço. As leituras são datadas no fuso horário do local na Espanha e exibidas no relógio do seu dispositivo. O Peek omite detalhes da entrega, fotos de comprovante de entrega (abra [gofo.com/es/](https://www.gofo.com/es/) para vê-las), referências pessoais, verificações de destinatário, pesos sem rótulo e avisos do portal.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a GOFO Spain mostra `El paquete está en reparto`, a cada 2 minutos a partir das 6h no relógio da leitura mais recente, enquanto o rastreador próprio responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, passa a consultar de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da GOFO Spain não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualização de rastreamento desde seg 12 out". A página do pacote então exibe "Encomendas costumam ficar sem atualizações em voos longos, na alfândega ou durante o fim de semana. O Peek continua consultando GOFO Spain.", com o link "Obter ajuda da GOFO Spain" para a página de contato oficial. Após três semanas sem novas leituras, mostra "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando GOFO Spain."

Você pode escolher alertas para "Cada leitura", "Etapas importantes" ou "Apenas entrega". O Peek monitora apenas atualizações de rastreamento e não pode exibir fotos de POD dentro do app, reagendar entregas nem abrir reclamações junto à GOFO Spain em seu nome.

## Perguntas sobre o rastreamento da GOFO Spain

### Quantas tentativas de entrega a GOFO Spain faz?

A GOFO Spain faz **três tentativas de entrega**. Após três tentativas falhas, a encomenda volta ao centro de distribuição e permanece guardada com segurança por **duas semanas**.

### O que significa `Devuelto al centro de clasificación`?

O código `204` (`Devuelto al centro de clasificación`) significa que o entregador levou o pacote de volta ao centro de triagem da GOFO após uma tentativa sem sucesso — ele **ainda não** foi devolvido ao vendedor original (isso aparece como `Devuelto`, códigos `257`/`264`).

### Onde posso ver minha foto de Comprovante de Entrega (POD) da GOFO Spain?

Digite seu código `GFES` ou `CIES` em [gofo.com/es/](https://www.gofo.com/es/) ou [gofo.com/es/tracking-results/](https://www.gofo.com/es/tracking-results/) para ver diretamente a foto de POD e as observações de local.

### Como entro em contato com o suporte da GOFO Spain?

Envie um e-mail para `cs@es.gofo.com`; a linha telefônica para destinatários ainda consta como em breve. Para pacotes confirmados como perdidos ou itens faltantes em embalagem intacta, fale diretamente com a loja vendedora.

:::sources
- [GOFO Spain: Página inicial em inglês](https://www.gofo.com/es/en/) – provedora de última milha fundada nos EUA, três tentativas de entrega e guarda por duas semanas no centro de distribuição
- [GOFO Spain: Página inicial em espanhol](https://www.gofo.com/es/) – portal de rastreamento em espanhol e acesso ao POD
- [GOFO Spain: Quem somos](https://www.gofo.com/es/en/who-we-are) – fundada em agosto de 2023
- [GOFO Spain: Envie conosco](https://www.gofo.com/es/ship-with-us) – parceiros de entrega nos EUA, Canadá, França, Países Baixos, Itália e Espanha
- [GOFO Spain: Resultados de rastreamento](https://www.gofo.com/es/tracking-results/) – portal de consulta de múltiplas encomendas
- [GOFO Spain: Suporte ao cliente (inglês)](https://www.gofo.com/es/en/contact-us/) – `cs@es.gofo.com`, telefone para destinatários (em breve, seg–sex `09:00–18:00`), passos para POD/extravio e regra de reclamação pelo remetente
- [GOFO Spain: Contacto (espanhol)](https://www.gofo.com/es/contact-us/) – página de contato em espanhol (URL de ajuda vinculada pelo Peek)
- [Comissão Europeia: Reforma Aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 de imposto aduaneiro por item em remessas até €150 desde 1º de julho de 2026 e taxa de manuseio da União de €2 desde 1º de novembro de 2026
- [Universal Parcel Scraper: gofo-es](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-es) – detecção `GFES`/`CIES` com dígito verificador `gofo-europe`, regras de link `gofo.com/es`, códigos numéricos (`100`, `200`–`208`, `257`/`264`), fusos das leituras, campos removidos e URL de ajuda
:::

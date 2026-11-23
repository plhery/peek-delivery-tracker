---
title: Rastreio Jadlog: código de 14 dígitos, Pickup e status
description: Rastreie sua encomenda na Jadlog: código de 14 dígitos, significado de cada status, pontos Jadlog Pickup, aviso Predict de 1 hora, franquias e atendimento.
slug: rastreio-jadlog
published: 2026-11-23
updated: 2026-11-23
---

A Jadlog é uma das maiores transportadoras de cargas expressas fracionadas e e-commerce do Brasil, integrante do grupo europeu Geopost (o mesmo de DPD, Chronopost, SEUR, BRT e Speedy). A empresa atua em todo o país com mais de 500 franquias cobrindo cerca de 5.000 municípios, mais de 7.000 veículos e mais de 4.000 estabelecimentos parceiros Jadlog Pickup. Você pode acompanhar seu pacote na caixa `RASTREIE SUA ENCOMENDA` da [página inicial da Jadlog](https://www.jadlog.com.br/jadlog/home) ou colar o código de 14 dígitos no campo acima.

## Código de rastreio Jadlog: formato de 14 dígitos e onde achar

O código de rastreio (ou número da remessa) aparece no e-mail de envio da loja online, na página do pedido no marketplace ou na etiqueta da encomenda. O padrão da Jadlog tem 14 dígitos numéricos seguidos:

| Formato (inventado) | O que é |
| --- | --- |
| `48291736405821` | Código de 14 dígitos da Jadlog. Compartilha o mesmo formato de 14 dígitos com outras marcas europeias do grupo Geopost/DPD (como DPD, Chronopost, SEUR e BRT). |

No site oficial da Jadlog também é possível consultar pelo CPF ou CNPJ do destinatário, mas o Peek rastreia apenas pelo código de 14 dígitos e nunca pede seu CPF. Se o seu código tiver 15 dígitos iniciados por `8880`, ele pertence à [J&T Express Brazil](carrier:j-and-t-br); códigos de 13 caracteres iniciados por `BR` são da [SPX Express Brazil](carrier:spx-br); códigos de 17 caracteres começando com `AJ` são da [Anjun Logistics](carrier:anjun); e códigos de 13 caracteres terminados em `BR` são dos Correios. Confira outros formatos em [formatos de código de rastreio](guide:tracking-number-formats) (em português de Portugal).

## O que significam os status de rastreio da Jadlog

Como a Jadlog integra a rede Geopost, as mensagens podem aparecer em português (no rastreador da Jadlog) ou em inglês (quando consultadas pela API do grupo Geopost).

| Status Jadlog / Geopost | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Order created`, `COLETA SOLICITADA` | A loja registrou o envio eletronicamente e pediu a coleta; a Jadlog ainda não bipou o pacote físico. | "Etiqueta criada" |
| `EMISSAO` | A Jadlog emitiu o documento de transporte e recebeu a encomenda na rede. | "Recebida pela transportadora" |
| `Em transferência` | A encomenda está em trânsito entre franquias, hubs de triagem ou caminhões de transferência. | "Em trânsito" |
| `Em rota de entrega` | O motorista saiu com a encomenda para entrega no seu endereço. | "Saiu para entrega" |
| `ENTREGUE` | A Jadlog registrou a entrega como concluída. | "Entregue" |

Quando o lojista gera etiquetas em lote antes da coleta do motorista, `Order created` ou `COLETA SOLICITADA` pode ficar como único status por alguns dias úteis. Em trechos rodoviários entre estados distantes, `Em transferência` também costuma ficar dias sem atualização até a leitura na franquia de destino.

## Serviços Jadlog: Predict, pontos Pickup e envios internacionais

Na página de [produtos e serviços](https://www.jadlog.com.br/jadlog/servicos) e em [Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos), a Jadlog detalha suas principais modalidades:

- **Jadlog.com e Jadlog Package:** entregas de e-commerce (Jadlog.com atende encomendas de até 30 kg; Jadlog Package cobre operações rodoviárias B2C e B2B).
- **Jadlog Rodo, RodoEconômico, Cargo e DOC:** transporte rodoviário até 50 kg (Rodo), modalidade econômica, carga aérea urgente B2B e documentos.
- **[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict):** disponível em regiões metropolitanas e áreas de alto volume, enviando aviso automático por SMS com uma janela estimada de 1 hora para a entrega.
- **[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup):** mais de 4.000 estabelecimentos comerciais parceiros (cobrindo mais de 90% da população brasileira) para retirada, postagem e logística reversa (*Reversa*), com notificações por SMS e e-mail quando o pacote está disponível.
- **[Jadlog Internacional](https://www.jadlog.com.br/jadlog/internacional):** exportação a partir do Brasil para 154 países (300 destinos) pela rede Geopost.

**O que a Jadlog não fixa publicamente.** O site da Jadlog não publica um único número padrão de tentativas de entrega, um prazo garantido único ou uma quantidade fixa de dias de guarda nos pontos Pickup para todas as lojas, porque essas condições variam conforme o contrato do remetente. Verifique o aviso por SMS ou e-mail do ponto Pickup — e a página do pedido na loja — para conferir o prazo de retirada e as regras de nova tentativa.

## Encomenda Jadlog parada, atrasada ou não recebida

Nas compras online, quem tem contrato de frete com a Jadlog é o vendedor: reembolso, alteração de endereço e reenvio passam pela loja.

**Parada em `Order created` ou `COLETA SOLICITADA`.** Os dados da remessa foram gerados, mas o pacote ainda não entrou de fato na rede da Jadlog. Confirme com a loja se a coleta já ocorreu.

**Sem atualização em `Em transferência`.** Viagens rodoviárias interestaduais, fins de semana e filas de triagem deixam o rastreio silencioso entre franquias. Veja o guia sobre [rastreio que não atualiza](guide:tracking-not-updating) (em português de Portugal).

**Consta `ENTREGUE`, mas não recebeu.** Pergunte antes a moradores, vizinhos e na portaria do prédio, e confira se o pedido foi enviado para um ponto Jadlog Pickup escolhido na compra. Se ninguém recebeu, avise a loja imediatamente para abrir acareação junto à transportadora. Veja [encomenda entregue mas não recebida](guide:delivered-not-received) (em português de Portugal).

**Avaria, extravio ou devolução.** Guarde a embalagem externa, fotografe o dano e a etiqueta e acione o vendedor; confira [encomenda danificada](guide:damaged-parcel) e [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender) (ambos em português de Portugal).

## Contato da Jadlog e caminhos para resolver problemas

A página de [Atendimento da Jadlog](https://www.jadlog.com.br/jadlog/atendimento) não exibe telefone público de SAC nem e-mail geral de suporte ao destinatário. Na prática, os caminhos disponíveis são:

- **Fale primeiro com a loja online:** o remetente tem os dados do contrato e do CT-e exigidos para abrir reclamação, corrigir endereço ou estornar o pedido.
- **Busca de unidades e franquias:** consulte a unidade responsável pela sua região em [Unidades e Franquias Jadlog](https://www.jadlog.com.br/jadlog/unidades).
- **Privacidade e dados pessoais (LGPD):** solicitações de titulares de dados têm canal próprio em [jadlog.com.br/jadlog/lgpd](https://www.jadlog.com.br/jadlog/lgpd).
- **Mediação de consumo:** se um problema com uma loja ou transportadora cadastrada não for resolvido, consumidores com conta gov.br prata ou ouro podem registrar reclamação gratuita no [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), com prazo de resposta de até 10 dias.

> Desconfie de mensagens por SMS, WhatsApp ou e-mail cobrando taxa extra por link para "liberar" ou "reagendar" uma entrega nacional da Jadlog. Confirme sempre qualquer cobrança diretamente com a loja onde você comprou. Veja [mensagens falsas de entrega](guide:delivery-scam-texts) (em português de Portugal).

## Rastrear uma encomenda Jadlog com o Peek

Como códigos de 14 dígitos são usados por várias empresas do grupo Geopost/DPD no mundo, o [Peek](/) usa sua localização para decidir qual consultar primeiro. No Brasil, o Peek pergunta primeiro à Jadlog (antes das marcas europeias da DPD, Chronopost, SEUR e BRT) e exibe "Jadlog está com esta encomenda." assim que ela confirma o código. Fora do Brasil, o Peek testa primeiro as transportadoras europeias da Geopost e consulta a Jadlog como alternativa se nenhuma reconhecer a remessa. Colar um link `jadlog.com.br/jadlog/captcha?cte=` seleciona a Jadlog direto em qualquer país; se precisar mudar manualmente, toque em "Trocar" ao adicionar ou em "Trocar transportadora" na tela da encomenda e escolha Jadlog (buscar "DPD Brazil" também encontra).

O Peek consulta a API pública do grupo Geopost para a Jadlog sem exigir CEP nem CPF. Mostra a etapa atual ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Entregue"), o histórico resumido de eventos (em inglês pela API da Geopost ou em português quando repassado do rastreio local) e o horário de entrega. Previsão de entrega, peso, endereço do ponto Pickup e nome ou assinatura de quem recebeu não ficam disponíveis no Peek.

O Peek consulta até a cada 10 minutos com o pacote aberto ou no dia da entrega com alertas ativados, e até a cada 2 minutos a partir das 6h no horário local no dia em que o pacote sai para entrega. Fora desses momentos, verifica de hora em hora, mantém consultas de hora em hora com a tela aberta após 12 horas em "Etiqueta criada" ou "Recebida pela transportadora" ou 2 dias sem novidade, e passa a cada 6 horas depois de 7 dias ainda em "Etiqueta criada". Quando serviços auxiliares de rastreamento entram como alternativa, consultam de hora em hora, a cada 3 horas após 1 dia sem mudança e até a cada 15 minutos no dia da entrega. Após 4 dias em trânsito sem atualização, o Peek destaca o silêncio (por exemplo, "Sem atualizações desde seg., 12 out.") e mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Jadlog." Depois de mais de três semanas sem novidades, aparece "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Jadlog."

Você escolhe os alertas em "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha o rastreamento e não agenda nova entrega nem entra em contato com franquias da Jadlog por você.

## Perguntas sobre o rastreio Jadlog

### Qual é o formato do código de rastreio da Jadlog?

O código da Jadlog tem 14 dígitos numéricos (por exemplo `48291736405821`, dígitos inventados). Você pode rastrear com esses 14 dígitos no Peek ou na [página inicial da Jadlog](https://www.jadlog.com.br/jadlog/home).

### O que é o Jadlog Predict?

O [Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) é um aviso automático por SMS, disponível em regiões metropolitanas e áreas de grande volume, que informa ao destinatário a previsão de entrega em uma janela de 1 hora.

### Como funciona o Jadlog Pickup?

O [Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup) reúne mais de 4.000 comércios parceiros no Brasil para retirada de compras online e postagem de devoluções (*Reversa*). Quando o pacote chega ao ponto escolhido, a Jadlog avisa por SMS e e-mail.

### Por que a Jadlog aparece ligada à DPD e à Geopost?

A Jadlog faz parte do grupo europeu Geopost, o mesmo das marcas DPD, Chronopost, SEUR, BRT e Speedy. Por isso utiliza numeração de 14 dígitos compatível com a rede Geopost e exporta para 154 países pelo grupo.

:::sources
- [Jadlog: Página inicial](https://www.jadlog.com.br/jadlog/home) – busca `RASTREIE SUA ENCOMENDA` e visão geral da malha brasileira
- [Jadlog: Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) – grupo Geopost, +500 franquias, +4.000 parceiros Pickup, +7.000 veículos, 7.000 colaboradores diretos e indiretos, 154 países
- [Jadlog: Produtos e Serviços](https://www.jadlog.com.br/jadlog/servicos) – Jadlog.com (até 30 kg), Package, Rodo (até 50 kg), RodoEconômico, Cargo e DOC
- [Jadlog: Pickup](https://www.jadlog.com.br/jadlog/pickup) – +4.000 pontos parceiros, alcance superior a 90% da população, avisos por SMS/e-mail e Reversa
- [Jadlog: Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) – aviso automático por SMS com janela de entrega de 1 hora
- [Jadlog: Internacional](https://www.jadlog.com.br/jadlog/internacional) – envios para 154 países / 300 destinos pela rede Geopost
- [Jadlog: Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) – localizador de franquias e unidades no Brasil
- [Jadlog: Atendimento](https://www.jadlog.com.br/jadlog/atendimento) – página pública de atendimento
- [Jadlog: LGPD](https://www.jadlog.com.br/jadlog/lgpd) – portal de privacidade e direitos do titular
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – plataforma pública de reclamações (prazo de 10 dias, conta gov.br prata ou ouro)
- [Universal Parcel Scraper: jadlog](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jadlog) – detecção de 14 dígitos e prioridade no Brasil (alias `DPD Brazil`), links `captcha?cte=`, API Geopost, mapeamento de status e frequência de atualização
:::

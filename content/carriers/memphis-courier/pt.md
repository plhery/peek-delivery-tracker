---
title: Rastreio Memphis Courier: códigos M-MIA, alfândega e apoio
description: Acompanha importações Memphis Courier dos EUA para o Brasil: códigos M-MIA, estados aeroporto GRU e ANVISA, regras de CPF e contactos oficiais.
slug: rastreio-memphis-courier
published: 2026-11-24
updated: 2026-11-24
---

A Memphis Courier é uma empresa logística 100 % brasileira com mais de 25 anos de história, tratando de envios expressos e formais de exportação e importação. A partir do seu terminal courier no Aeroporto Internacional de Guarulhos (GRU) e do escritório em São Paulo, oferece transporte aéreo, desalfandegamento na Receita Federal, aprovação regulatória junto da ANVISA (incluindo importações de canábis medicinal ao abrigo das normas RDC 660 e RDC 327 para medicamentos fabricados nos Estados Unidos, Europa, Colômbia e Uruguai) e entrega em todo o Brasil.

O rastreio acompanha a encomenda até ao aeroporto de Guarulhos, durante a inspeção da Receita Federal e a análise da ANVISA, e depois até à entrega.

## Formato do número de rastreio Memphis Courier

Os números de rastreio Memphis Courier seguem um formato alfanumérico característico de 11 carateres:

- **`M` + 7 dígitos + `MIA`** (11 carateres no total, como `M4829173MIA`) — o formato que o Peek reconhece como pertencente à Memphis Courier.

O Peek aceita códigos Memphis Courier de 6 a 30 letras e dígitos; remove espaços e converte letras em maiúsculas. O portal de rastreio da Memphis Courier (`clients.memphiscourier.com.br`) pesquisa códigos sem início de sessão de cliente. A partir do formato `M` + 7 dígitos + `MIA`, o Peek pergunta apenas à Memphis Courier e mostra «Memphis Courier tem esta encomenda.» assim que responde. Para outros formatos habituais em importações para o Brasil, consulta o [guia de formatos de números de rastreio](guide:tracking-number-formats).

## Estados de rastreio Memphis Courier

O serviço de rastreio da Memphis Courier devolve códigos de 3 letras com descrições em português e uma coluna de localização (estado ou país). A barra de etapas do site percorre Criado (`FLT`), alfândega (`BRP`), Liberado e Entregue; os registos que atingem os dois últimos passos (`CLA` e `DLV`) só são identificados por essas etiquetas.

| Código | Texto da Memphis Courier | O que significa |
| --- | --- | --- |
| `FLT` | Seu pedido foi criado com sucesso | A ordem de envio foi registada no sistema da Memphis Courier antes da partida aérea. |
| `CHK` | Entrada registrada no Centro de Distribuição | Entrada registada num centro de distribuição da Memphis Courier. |
| `ENO` | Encomenda em trânsito | Em circulação na rede da Memphis Courier. |
| `TRA` | Encomenda em transferência | Em transferência entre instalações de tratamento. |
| `DEP` | A encomenda saiu da origem e está a caminho do aeroporto de destino (Guarulhos - SP) | Partiu do hub de origem num voo com destino ao Aeroporto Internacional de Guarulhos (GRU). |
| `ARR` | Seu pedido chegou ao aeroporto de destino (Guarulhos - SP) | Chegou ao aeroporto de Guarulhos em São Paulo. |
| `AAZ` | Pedido recebido pela GRU Airport | Recebida pelo terminal de carga do aeroporto GRU antes da entrada formal em alfândega. |
| `BRP` | Processo de desembaraço iniciado | O desalfandegamento brasileiro começou. |
| `HOC` | Encomenda sendo inspecionada pela Alfândega | Em inspeção pela Receita Federal (alfândega brasileira). |
| `ANV` | Em inspeção pela ANVISA | Em inspeção regulatória pela ANVISA, a agência sanitária do Brasil, durante o desalfandegamento. |
| `CLA` | Liberado (barra de etapas) | A alfândega e as entidades reguladoras libertaram o envio para entrega nacional. |
| `DLV` | Entregue (barra de etapas) | Entregue na morada do destinatário no Brasil. |

## Alfândega, validação de CPF e autorização ANVISA

Todas as importações expressas pessoais no Brasil exigem o CPF (*Cadastro de Pessoas Físicas*) do destinatário para o desalfandegamento na Receita Federal. Como refere a Memphis Courier na sua [página de serviços adicionais](https://memphiscourier.com.br/servicos-adicionais/), muitas lojas online estrangeiras não têm campo de CPF no pagamento; quando isso acontece, o apoio ao cliente da Memphis Courier contacta o destinatário para validar o CPF e evitar retenções.

Para canábis medicinal e outros medicamentos importados pela Memphis Courier dos Estados Unidos, Europa, Colômbia ou Uruguai, a encomenda pode passar tanto por controlo da Receita Federal (`HOC`) como da ANVISA (`ANV`) em Guarulhos antes de surgir `CLA` (libertada). Se o teu envio ficar em `BRP`, `HOC` ou `ANV` durante vários dias úteis sem mudança, verifica o e-mail e o WhatsApp: a Memphis Courier mantém os clientes informados por e-mail, WhatsApp, chat ou telefone e pode ter pedido confirmação de CPF ou um documento. Para mais detalhes sobre desalfandegamento internacional, consulta o [guia da alfândega](guide:customs) e [quando o rastreio deixa de atualizar](guide:tracking-not-updating).

Assim que a alfândega liberta a encomenda (`CLA`), a distribuição nacional cobre todos os estados brasileiros. Se outra transportadora entregar uma encomenda separada no Brasil, compara o rastreio com [Anjun Express](carrier:anjun), [Jadlog](carrier:jadlog) ou [J&T Express Brazil](carrier:j-and-t-br).

## Seguir a Memphis Courier com o Peek

Cola um código `M...MIA` no Peek e o Peek associa-o automaticamente à Memphis Courier assim que a transportadora responde. Se precisares de escolher ou trocar a transportadora manualmente, usa **Alterar** ao adicionar a encomenda ou **Alterar transportadora** no ecrã de rastreio.

O Peek consulta diretamente o serviço público de rastreio da Memphis Courier através do [adaptador aberto Memphis Courier do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier). Lê o código, o texto em português, a localização e o peso total em quilogramas quando disponível. A Memphis Courier fornece a hora local de cada registo e só inclui fuso UTC quando a hora termina com ele. O Peek respeita esse fuso; caso contrário data o registo no fuso horário do local (hora de Nova Iorque para `Estados Unidos`, hora de São Paulo para `SP` ou `Brasil`), um registo sem local no fuso vizinho ou então na hora de São Paulo, mostrando tudo na hora do teu dispositivo. Campos do portal de clientes como notas internas, número de volumes, códigos postais, destino e documentos aduaneiros anexos permanecem no site da Memphis Courier.

O Peek agrupa os 12 códigos da Memphis Courier em etapas claras: **Etiqueta criada** para `FLT`, **Em trânsito** de `CHK` a `AAZ` e novamente após a libertação aduaneira em `CLA` (o conjunto de códigos da Memphis Courier passa diretamente da criação do pedido para trânsito em centro de distribuição, sem um código separado de **Aceite pela transportadora**), **Na alfândega** para `BRP`, `HOC` e `ANV`, e **Entregue** assim que surge `DLV`.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos enquanto o rastreio da Memphis Courier responder. Caso contrário, o Peek verifica de hora a hora. Como o rastreio da Memphis Courier não mostra uma etapa de saída para distribuição, o Peek nunca passa às verificações de 2 em 2 minutos no dia da entrega. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da Memphis Courier não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia da distribuição enquanto tens a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg., 12/10». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Memphis Courier.», com uma ligação «Pede ajuda a Memphis Courier» para a página Fale Conosco da Memphis Courier. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Memphis Courier.»

Escolhe **Todos os registos**, **Passos importantes** ou **Só a entrega** nas preferências de alertas do Peek para definir com que frequência pretendes receber notificações.

## Contactar a Memphis Courier

A Memphis Courier publica canais oficiais de apoio no seu site e declara explicitamente no aviso oficial que não considera plataformas de reclamações de terceiros como o Reclame Aqui um canal oficial de atendimento ou protocolo.

- **Página oficial de contacto:** [Fale Conosco](https://memphiscourier.com.br/fale-conosco/) (formulário de contacto)
- **Telefone / apoio ao cliente:** +55 11 94264-7550 (indicado tanto para o Terminal Courier GRU como para o escritório de São Paulo)
- **E-mail:** `atendimento@memphiscourier.com.br`, `atendimento1@memphiscourier.com.br` e `info@memphiscourier.com.br`
- **Terminal Courier de Guarulhos:** Aeroporto Internacional de Guarulhos — Terminal Courier, CEP 07190-100, São Paulo, Brasil
- **Escritório de São Paulo:** Av. Marques de São Vicente, 230, cj 1216 — Barra Funda, CEP 01139-000, São Paulo, Brasil ([Quem Somos](https://memphiscourier.com.br/quem-somos/))

Tem contigo o código `M...MIA` e o CPF do destinatário ao contactar o apoio para que a equipa localize de imediato o teu processo aduaneiro.

## Perguntas frequentes

### O que significa `ANV` («Em inspeção pela ANVISA») na Memphis Courier?

`ANV` significa que a agência sanitária brasileira (ANVISA) está a inspecionar o envio durante o desalfandegamento de importação. A Memphis Courier desalfandega importações de canábis medicinal (RDC 660 / RDC 327) junto da ANVISA; a encomenda permanece na alfândega até `CLA` (libertada).

### Por que me contactou a Memphis Courier a pedir o meu CPF?

A Receita Federal exige o CPF do destinatário em todas as importações expressas pessoais para o Brasil. Quando uma loja estrangeira não pede o CPF no checkout, o apoio ao cliente da Memphis Courier contacta-te para o validar antes de avançar com o desalfandegamento.

### Por que não há registos entre `DEP` e `ARR`?

`DEP` regista a partida da origem rumo ao aeroporto de Guarulhos e `ARR` a chegada lá. A lista de códigos da Memphis Courier não inclui registos durante o voo, pelo que o evento seguinte é `ARR` ou `AAZ` no terminal de carga.

### A Memphis Courier responde a pedidos publicados no Reclame Aqui?

Não. O aviso oficial no site da Memphis Courier esclarece que trata reclamações, dúvidas e esclarecimentos exclusivamente através dos canais oficiais publicados em [memphiscourier.com.br](https://memphiscourier.com.br/) (telefone, e-mail e formulário de contacto) para garantir rastreabilidade e confidencialidade dos dados.

:::sources
- [Memphis Courier](https://memphiscourier.com.br/)
- [Memphis Courier — Quem Somos](https://memphiscourier.com.br/quem-somos/)
- [Memphis Courier — Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/)
- [Memphis Courier — Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- [adaptador Memphis Courier do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)
:::

---
title: Rastreamento Posta Shqiptare: códigos AL, status e ajuda
description: Rastreie encomendas ou EMS da Posta Shqiptare (Correios da Albânia): formato do código AL, significado dos status em albanês, alfândega e contato de suporte.
slug: posta-shqiptare-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Posta Shqiptare (Correios da Albânia) é o serviço postal nacional da Albânia, com 533 agências distribuídas pelo país. Ela opera correspondências e encomendas dentro da Albânia, vindas do exterior e enviadas para outros países, incluindo EMS. Rastreie um objeto na caixa "Gjurmo objektin" (rastrear objeto) em [postashqiptare.al](https://www.postashqiptare.al/) ou no aplicativo e-Posta, ou cole o código na caixa acima. Em compras on-line, peça o código ao vendedor.

## Código de rastreamento da Posta Shqiptare: como ele é

A Posta Shqiptare rastreia objetos por um código de barras de 13 caracteres: duas letras para o tipo de serviço, nove dígitos e duas letras do país onde o objeto foi postado. O último dos nove dígitos é verificador. Com dígitos inventados:

| Formato (inventado) | O que é |
| --- | --- |
| `RR482917367AL` | Começa com C, E, L ou R e termina em `AL`: objeto postado na Albânia. |
| `UA619402838AL` | Outras letras iniciais, mas terminado em `AL`: também numerado na Albânia. |
| `RR739402812CN` | Termina em outras letras (aqui `CN`, da China): numerado pelo correio de outro país. Encomendas destinadas à Albânia costumam trazer esse tipo de código, e o rastreador da Posta Shqiptare também o acompanha. |

O campo de busca no site da Posta Shqiptare aceita somente esse formato; qualquer outro retorna `Kodi eshte i gabuar!` (o código está errado). Sem código? A Posta Shqiptare orienta perguntar no balcão da agência que distribui sua correspondência informando seu nome e sobrenome. Veja [como encontrar um código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da Posta Shqiptare explicados

As linhas de postagem e triagem interna da Posta Shqiptare aparecem em albanês com inglês após a barra; já o despacho para o exterior e as leituras repassadas pelo correio de destino aparecem apenas em albanês. As mensagens conhecidas pelo Peek, aproximadamente em ordem:

| A Posta Shqiptare exibe | O que significa para você |
| --- | --- |
| `Pranim objekti nga klienti / Receive item from customer` | Postado em uma agência da Posta Shqiptare. |
| `Objekti u nxor nga thesi per perpunim / Receive item at location (Inb)` | Retirado do malote para triagem em agência ou centro postal na Albânia, logo após a postagem e antes de deixar o país, embora a parte em inglês traga `(Inb)`, de entrada. |
| `Objekti u fut ne thes dhe u dergua jashte` | Colocado em malote e expedido ao exterior pelo escritório de permuta da Albânia: está saindo do país. |
| `Objekti u pranua ne zyren e shkembimit` | O correio do país de destino o recebeu em seu escritório de permuta: chegou àquele país. |
| `Regjistrimi i informacionit doganor te objektit` | Dados aduaneiros registrados: está com a alfândega local ou retido por ela. |
| `Objekti u dergua ne zyren destinacion` | Encaminhado do escritório de permuta para a unidade de entrega. |
| `Objekti u pranua ne zyren e shperndarjes` | Chegou à agência de distribuição. |
| `Tentative e pasuksesshme ne shperndarjen e objektit` | Entrega não realizada; pode haver nova tentativa. |
| `Objekti u dorezua` | Entregue. |

Alguns objetos EMS enviados ao exterior seguem por transportadora parceira, e a Posta Shqiptare repassa essas leituras em albanês:

| Linha da transportadora | O que significa para você |
| --- | --- |
| `Nisur nga origjina` | Saiu da Albânia. |
| `Mbajtur ne dogane` | Retido na alfândega do país de destino. |
| `Mbërritur në qendrën e shpërndarjes` | Chegou ao centro de distribuição da transportadora. |
| `Marrë nga korrieri` | Recolhido pelo entregador (não indica necessariamente saída para entrega). |
| `Dorëzuar` | Entregue. |

O Peek organiza essas leituras em suas próprias etapas: "Recebida pela transportadora", "Em trânsito", "Na alfândega", "Tentativa de entrega" e "Entregue". Qualquer linha ainda não mapeada aparece exatamente como escrita pela Posta Shqiptare. Mais detalhes em [o que significam os status de rastreamento](guide:tracking-statuses).

## Recebendo uma encomenda do exterior na Albânia

**Alfândega.** A Posta Shqiptare realiza desembaraço prévio para objetos do exterior com mercadorias acima de €22, ou presentes acima de €44, até €1.000 por item. O volume segue para a agência postal central da cidade de destino e você recebe um aviso por escrito; lá você paga o imposto aduaneiro e a taxa de elaboração da declaração e retira o objeto na hora. Duas páginas da Posta Shqiptare divergem sobre a alíquota: a página aduaneira informa 22,4% do valor declarado para compras on-line acima de €22, enquanto o FAQ cita de 22,4% a 38% para objetos declarados acima de €22 (preço mais frete como valor aduaneiro). Só é preciso ir ao posto aduaneiro na filial Transit Centre caso a alfândega retenha o objeto para inspeção ou identifique itens proibidos. Mais detalhes em [encomendas retidas na alfândega](guide:customs).

**Prazos de entrega na Albânia.** Padrões da Posta Shqiptare para itens postados antes do último horário de coleta da agência: até as 14h do dia seguinte na mesma cidade, no dia seguinte (em até 24 horas) nas demais cidades e, em média, até 3 dias úteis para vilarejos.

## Encomendas da Posta Shqiptare para o exterior: serviços e entrega

**Serviços.** Pequenos pacotes (*paketë e vogël*) de mercadorias vão até 2 kg e encomendas postais (*kolipostë*) até 20 kg. O EMS transporta documentos e mercadorias até 20 kg para cerca de 200 destinos em 2 a 5 dias, segundo a Posta Shqiptare.

**Tracked.** Desde 3 de janeiro de 2026, pequenos pacotes de mercadorias até 2 kg seguem ao exterior pelo serviço Tracked, que substituiu o registrado para esses volumes. Apenas três leituras são obrigatórias: saída do escritório de permuta da Albânia, chegada ao escritório de permuta do destino e chegada à agência de entrega, tentativa de entrega ou entrega. O Tracked não exige assinatura na entrega (apenas leitura de entrega) e não oferece indenização por perda ou dano.

**Prazos internacionais.** Os padrões da Posta Shqiptare para pacotes e encomendas são de 7 a 10 dias úteis em média para a Europa e de 10 a 14 dias úteis para Américas, Ásia e África.

**Quem entrega.** Depois de sair da Albânia, o correio do país de destino assume o objeto, e o rastreador da Posta Shqiptare repassa as leituras desse correio em albanês. Consulte também o rastreamento do seu próprio correio com o mesmo código `AL`: [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post) ou NZ Post. Tentativas de entrega e retirada seguem as regras do seu correio: veja [entrega não realizada](guide:missed-delivery).

**Alfândega na UE.** Como a Albânia fica fora da União Europeia, encomendas enviadas para Irlanda, Itália, Grécia ou qualquer outro país da UE passam pela alfândega. Desde 1º de julho de 2026, compras on-line vindas de fora da UE em pedidos de até €150 pagam imposto aduaneiro da UE de €3 por item. Desde 1º de novembro de 2026, aplica-se também a taxa de manuseio da União de €2 por item, qualquer que seja o valor. Um item corresponde às mercadorias no pacote que compartilham classificação tarifária, descrição e, quando exigido, origem. Em outros países, valem as regras locais.

## Rastreamento da Posta Shqiptare sem atualização, atrasado ou perdido

**Sem novidades após sair da Albânia.** Um pacote Tracked exige apenas três leituras, então passar dias sem atualização é normal. Assim que chegar, consulte o correio do seu país com o mesmo código: veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Atrasado e parado.** O remetente deve ir pessoalmente à agência onde postou o objeto e preencher um formulário de investigação com os dados de ambos, o conteúdo e um telefone de contato. A Posta Shqiptare então consulta o correio do país de destino.

**Reclamações.** O remetente pode reclamar a não chegada em até 3 meses após a postagem para envios dentro da Albânia, ou 6 meses para envios internacionais. Para envios ao exterior é gratuito, mediante formulário na agência de postagem. A Posta Shqiptare responde em até 15 dias (ou 2 meses para envios internacionais). Se o pacote está vindo para você, peça ao remetente para verificar junto ao correio de origem, ou fale com a loja em compras on-line: veja [encomendas perdidas](guide:lost-parcel).

**Marcada como entregue, mas não recebida.** Um pacote Tracked é entregue sem assinatura; procure primeiro o correio responsável pela entrega final: veja [entregue, mas não recebida](guide:delivered-not-received).

## Telefone e atendimento ao cliente da Posta Shqiptare

Canais publicados pela Posta Shqiptare:

- **Telefone:** +355 682044727.
- **E-mail:** asistence@postashqiptare.al.
- **Sede:** Drejtoria e përgjithshme Posta Shqiptare, Rruga "Reshit Çollaku" nr. 4, Tirana. Horário indicado logo abaixo na página de contato: segunda a quinta-feira das 08:00 às 16:30 e sexta-feira das 08:00 às 14:00 (horário da Albânia).
- **Reclamações sobre envios internacionais:** mediante formulário na agência onde o objeto foi postado.

A Posta Shqiptare alerta contra mensagens sobre seus serviços que não partem dela: não clique nos links nem informe seus dados. A empresa divulga avisos oficiais apenas em postashqiptare.al e em suas redes sociais. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Acompanhe uma encomenda da Posta Shqiptare com o Peek

O [Peek](/) reconhece códigos iniciados por `C`, `E`, `L` ou `R`, seguidos de outra letra, nove dígitos e `AL` com dígito verificador válido como Posta Shqiptare e exibe "Transportadora detectada"; "Trocar" escolhe outra. Com dígito verificador incorreto, avisa "O dígito verificador deste código não confere.": confira o código recebido ou escolha "Rastrear como digitado". Links do rastreador e-Posta também funcionam. Cadastrada na Posta Shqiptare, a encomenda mostra "Aguardando Posta Shqiptare" até a primeira leitura.

Códigos terminados em `AL` que começam por outra letra, como `U` ou `V`, não são atribuídos à Posta Shqiptare apenas pelo formato. Cole-os mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Durante a verificação, exibe "Consultando serviços de rastreamento…", consulta primeiro a Posta Shqiptare e mostra "Posta Shqiptare está com esta encomenda." quando ela reconhece o número.

Encomendas destinadas à Albânia com código de outro correio ficam registradas sob aquele correio, e o Peek não consulta a Posta Shqiptare automaticamente. Para acompanhá-las no rastreador da Posta Shqiptare, abra a encomenda, toque em "Trocar transportadora" e escolha Posta Shqiptare: o Peek mantém o histórico enquanto consulta.

O Peek exibe cada linha com a redação original da Posta Shqiptare e identifica leituras no exterior pelo nome do país em inglês. Ele remove nomes de agências que incluam código postal e qualquer trecho após o texto de entrega que possa revelar quem recebeu. Não exibe data estimada de entrega, pois a Posta Shqiptare não fornece essa informação. O Peek interpreta as leituras próprias da Posta Shqiptare no horário da Albânia e determina o fuso das leituras no exterior pelo local informado ou pelas leituras vizinhas.

Quando você rastreia de outro país, o Peek também consulta o correio local com o mesmo código `AL`: USPS, Royal Mail ([Parcelforce](carrier:parcelforce) para códigos iniciados por `E`), An Post, Canada Post, Australia Post, NZ Post, [DHL](carrier:dhl) na Alemanha, [Poste Italiane](carrier:poste-italiane), ELTA na Grécia e outros. Assim que esse correio apresenta uma leitura não anterior à mais recente da Posta Shqiptare, o Peek passa para ele preservando o histórico albanês. A partir de Kosovo, Montenegro ou Turquia, não consulta nenhum outro correio.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta a Posta Shqiptare diretamente até a cada 10 minutos enquanto o rastreador responder; fora isso, a cada hora. Após dois dias sem novidades (12 horas ainda em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas depois de sete dias em "Etiqueta criada". Após quatro dias sem nova movimentação em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", destaca o dia daquela leitura com "Sem atualizações desde …", a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Posta Shqiptare." e o link "Pedir ajuda a Posta Shqiptare"; após três semanas, exibe "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Posta Shqiptare." Alertas: "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a Posta Shqiptare, o seu correio nem a loja por você.

## Perguntas sobre o rastreamento da Posta Shqiptare

### Como rastrear uma encomenda da Posta Shqiptare?

Digite o código de 13 caracteres na caixa "Gjurmo objektin" em postashqiptare.al, no aplicativo e-Posta ou cole-o no Peek. Encomendas destinadas à Albânia são rastreadas pelo número do correio de origem.

### Por que o rastreamento da Posta Shqiptare não atualiza?

Um pacote Tracked exige apenas três leituras obrigatórias e, depois que sai da Albânia, os próximos registros vêm do correio do país de destino: consulte o rastreamento dele com o mesmo código. Se nada mudar por muito tempo, o remetente pode abrir investigação na agência onde postou.

### Quanto tempo a Posta Shqiptare leva para entregar?

Pelos próprios padrões, dentro da Albânia entrega no dia seguinte entre cidades e em até 3 dias úteis em média em vilarejos. Para o exterior, pequenos pacotes e encomendas levam em média 7 a 10 dias úteis para a Europa e 10 a 14 para Américas, Ásia e África; o EMS leva de 2 a 5 dias, segundo a empresa.

### Qual é o telefone da Posta Shqiptare?

+355 682044727, ou e-mail asistence@postashqiptare.al. A página de contato indica atendimento de segunda a quinta-feira das 08:00 às 16:30 e sexta-feira das 08:00 às 14:00 logo abaixo do endereço da sede.

:::sources
- [Posta Shqiptare: Home](https://www.postashqiptare.al/) – campo de rastreamento, formato do código e agências postais
- [Posta Shqiptare: Pyetje/Përgjigje (FAQ)](https://www.postashqiptare.al/c/71/pyetje-prgjigje) – correspondências do exterior, código de barras, investigações e alfândega
- [Posta Shqiptare: Çështje doganore (customs)](https://www.postashqiptare.al/c/43/shtje-doganore) – desembaraço prévio, aviso e imposto aduaneiro
- [Posta Shqiptare: Tracked](https://www.postashqiptare.al/c/207/tracked) – serviço Tracked, leituras, assinatura e indenização
- [Posta Shqiptare: EMS](https://www.postashqiptare.al/c/39/sherbim-postar-ekspres-i-jashtem-ems) – peso, destinos e prazos do EMS
- [Posta Shqiptare: Shërbim Postar Universal](https://www.postashqiptare.al/c/37/shrbim-postar-universal) – pequenos pacotes e encomendas
- [Posta Shqiptare: Universal service standards](https://www.postashqiptare.al/c/82/informacion-pr-klientin-pr-standardet-e-shrbimit-universal-postar) – prazos de entrega
- [Posta Shqiptare: Përse e-Posta?](https://www.postashqiptare.al/c/110/prse-e-posta) – aplicativo e-Posta
- [Posta Shqiptare: Ankimim (complaints)](https://www.postashqiptare.al/c/157/ankimim) – prazos de reclamação, respostas e formulário
- [Posta Shqiptare: Kontakto Postën Shqiptare](https://www.postashqiptare.al/c/70/kontakto-postn-shqiptare) – telefone, e-mail, endereço e horários
- [Posta Shqiptare: !!KUJDES !! (warning)](https://www.postashqiptare.al/d/355/kujdes-) – aviso sobre mensagens falsas
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto de €3 e taxa de manuseio de €2
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobrança por item e definição de item
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – redação das leituras, o que o Peek lê e qual correio consulta em seguida
:::

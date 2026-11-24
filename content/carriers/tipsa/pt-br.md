---
title: Rastreamento TIPSA: código de 22 dígitos, status e contato
description: Rastreie encomendas da TIPSA na Espanha e em Portugal: código de 22 dígitos, status em espanhol traduzidos, reentrega, agências, pontos Pickup e contato.
slug: rastreamento-tipsa
published: 2026-11-24
updated: 2026-11-24
---

A TIPSA é uma transportadora expressa espanhola do grupo Geopost, com agências na Espanha, em Portugal e em Andorra. Você pode rastrear uma encomenda com o código de 22 dígitos no [localizador de entregas](https://www.tip-sa.com/en/delivery-locator) da TIPSA ou colá-lo na caixa acima. O código vem da loja ou de quem enviou o pacote, geralmente no e-mail ou SMS de envio. Se não tiver o número, fale com a loja ou com a agência da TIPSA que entrega na sua região — encontre-a pelo código postal no [buscador de agências](https://www.tip-sa.com/es/agencias-de-transporte). Também é com essa agência que você combina uma nova entrega após um `Ausente` ou combina a retirada presencial; em caso de extravio ou avaria, avise a loja.

## Como é o código de rastreamento da TIPSA

O localizador da TIPSA pede o número de envio de 22 dígitos e também marca o código postal de destino como obrigatório. O código é formado por dois códigos de agência seguidos do número do conhecimento (*albarán*):

:::anatomy 028145 028145 4829105736
- 028145 | Primeiro código de agência: uma agência TIPSA ligada ao envio
- 028145 | Segundo código de agência: outra agência da rota do envio
- 4829105736 | Conhecimento (albarán): o número próprio da remessa
:::

| Formato (fictício) | O que é |
| --- | --- |
| `0281450281454829105736` | O código completo da TIPSA com 22 dígitos. Use este formato. |
| `028145 028145 4829105736` | O mesmo código com espaços. O Peek aceita; o campo do site da TIPSA só comporta 22 caracteres, então digite sem espaços lá. |
| `4829105736` | Apenas o *albarán* de 10 dígitos. Nem o localizador da TIPSA nem o Peek aceitam esse formato para a TIPSA. |

As lojas podem configurar a TIPSA para mandar e-mails e SMS quando o pacote sai, chega à agência, sofre uma ocorrência ou é entregue, com links diretos para a página da remessa. Sem o número de 22 dígitos, o localizador da TIPSA permite buscar pela referência do envio, mas exige um identificador (agência + cliente) que só o remetente pode informar. Veja mais dicas em [como encontrar seu código de rastreamento](guide:find-tracking-number).

Códigos de 22 dígitos também combinam com Austrian Post, [USPS](carrier:usps) e Estafeta. Quando ambos os códigos de agência começam com `00`, o formato coincide com o da [CTT Express](carrier:ctt-express), outra transportadora espanhola — confirme no e-mail da loja qual transportadora foi usada. Muita gente busca por "TIPSA 10 dígitos", mas apenas a forma completa de 22 dígitos funciona: procure-a no e-mail ou peça ao remetente.

## O que significam os status de rastreamento da TIPSA

A página de detalhes da TIPSA fica apenas em espanhol, mesmo abrindo o localizador pelo site em inglês. A parte superior traz uma linha do tempo curta com rótulos como `Documentado`, `En camino`, `En reparto` e `Entregado`; abaixo, uma tabela lista cada leitura com data, hora e cidade. Estes são os status exibidos:

| Status da TIPSA | O que significa |
| --- | --- |
| `PENDIENTE DE ENTREGAR A TIPSA` | A loja registrou a remessa, mas a TIPSA ainda não recebeu o volume: um [status de etiqueta criada](guide:tracking-statuses). |
| `TRANSITO` | Em trânsito entre agências e centros de triagem da TIPSA. |
| `LECTURA EN HUB MADRID` | Lido num hub da TIPSA; o nome varia conforme a unidade (`LECTURA EN HUB BARCELONA`, `LECTURA EN HUB BARBERA`). |
| `Pendiente de llegada` | Ainda não chegou à agência responsável pela entrega. |
| `LEIDO EN DESTINO` | Registrado na unidade de destino. |
| `LECTURA EN AGENCIA DESTINO` | Lido na agência entregadora, seguido do nome dela. |
| `REPARTO` | Saiu para entrega. |
| `Ausente` | O entregador não conseguiu entregar porque ninguém atendeu. |
| `Faltan Datos / Datos incorrectos` | Faltam dados no endereço ou há informações erradas; anotado na rota antes de nova tentativa. |
| `Cerrado` | O endereço estava fechado na hora da visita; anotado na rota após `REPARTO`. |
| `Solicitada en otra dirección` | Foi pedida entrega em outro endereço e a encomenda está sendo redirecionada. |
| `DISPONIBLE` ou `Pendiente de recoger en Tipsa` | Após entrega sem sucesso, o pacote aguarda retirada na agência. |
| `Devolver a Origen` | Em devolução ao remetente. |
| `ENTREGADO` | Entregue. |

`Ausente` traz uma nota da própria TIPSA informando que não foi possível contatar você, que haverá nova tentativa no dia útil seguinte e que você pode ligar para a agência de destino para combinar a entrega. O nome e o endereço dessa agência aparecem em "AGENCIA DESTINO".

## Tentativa falha, agências e pontos Pickup da TIPSA

A TIPSA opera com rede própria na Espanha e em Portugal continentais, Ilhas Baleares, Canárias, Ceuta e Melilla, e no exterior via DPDgroup. Após um `Ausente`, a segunda tentativa ocorre no dia útil seguinte; a tarifa paga pelas lojas inclui duas tentativas, ou três no serviço Premium para e-commerce.

**Retirada na agência.** Se você for viajar ou preferir retirar pessoalmente, o FAQ da TIPSA informa que a encomenda fica na agência mais próxima do seu código postal por 15 dias corridos a partir da postagem — não da chegada. Depois disso, volta ao remetente. Leve seu documento oficial (DNI ou passaporte); terceiros precisam levar uma fotocópia do seu documento. O [buscador de agências](https://www.tip-sa.com/es/agencias-de-transporte) pesquisa por província ou código postal.

**Alteração de endereço.** Segundo a TIPSA, apenas o remetente pode mudar endereço ou dados da entrega, então solicite à loja.

**Pontos Pickup e lockers.** A TIPSA compartilha a rede de pontos Pickup com a [SEUR](carrier:seur), em comércios parceiros e lockers abertos 24 horas. A maioria dos pontos abre pelo menos seis dias por semana e guarda o pacote por 7 dias. A entrega em ponto precisa ser oferecida pela loja no checkout; consulte locais no [mapa de pontos Pickup](https://aplicaciones.tip-sa.com/puntospickup/).

Algumas lojas contratam entrega aos sábados ou pré-alerta (SMS e e-mail avisando o dia em que o pacote chega ao destino). No serviço Premium também é possível escolher uma janela horária (com margem de uma hora); como a TIPSA não informa onde selecionar, pergunte à agência de destino.

**Pagamento na entrega.** No pagamento contra entrega (*contrarreembolso*), a TIPSA cobra na entrega o valor definido pela loja, até €1.000 por remessa; no frete a cobrar (*portes debidos*), cobra o frete no destino. A empresa não especifica se o pagamento é em dinheiro ou cartão: confirme com a loja ou a agência. Ambos são pagos presencialmente no recebimento — portanto, SMS ou e-mail "da TIPSA" pedindo pagamento por link não corresponde a nenhum deles. Não pague e confira o número direto em tip-sa.com. Saiba mais em [golpes de SMS de entrega](guide:delivery-scam-texts).

## Encomenda atrasada, extraviada ou devolvida na TIPSA

As páginas da TIPSA não detalham o que fazer quando o rastreamento para de se mover. Ligue para a agência de destino indicada na página da remessa ou fale com a loja; [rastreamento que não atualiza](guide:tracking-not-updating) ajuda a avaliar quando agir.

Para extravio ou avaria, o FAQ da TIPSA é direto: contate o remetente e avise o ocorrido. A loja detém o contrato com a TIPSA e abre a reclamação; a transportadora não publica procedimento nem prazo de indenização para destinatários. A TIPSA também não tem página para pacote marcado como `ENTREGADO` que não chegou — avise o remetente nesse caso também.

Pacotes não retirados em até 15 dias da postagem voltam ao remetente; combine reembolso ou reenvio com a loja.

## Telefone e atendimento da TIPSA

Canais da TIPSA listados na página de contato em inglês e no FAQ:

- **Telefone:** 902 10 10 47 ou 900 10 10 47. O FAQ em espanhol cita apenas o 900 10 10 47. A TIPSA não informa horários de atendimento nem tarifas.
- **Formulário de contato** em [tip-sa.com/en/contact](https://www.tip-sa.com/en/contact), selecionando o motivo "I have a problem with my shipment" e informando o número de envio.
- **E-mail:** info@tip-sa.com, indicado no FAQ para código de envio ausente.
- **Agência de destino**, exibida na página da remessa, para agendar ou combinar a entrega.

Portugal não tem linha separada: a página portuguesa de contato exibe os mesmos dois telefones. As páginas do aplicativo da TIPSA continuam no ar, mas os links nas lojas de apps não carregam mais.

## Como rastrear encomendas da TIPSA no Peek

O [Peek](/) aceita o código de 22 dígitos com ou sem espaços. Como outras transportadoras compartilham esse formato, o Peek consulta as prováveis ao mesmo tempo, começando pela TIPSA: você vê "consultando…" e depois "TIPSA está com esta encomenda" quando a TIPSA reconhece o código. Um código cujos dois códigos de agência começam com `00`, com o segundo na lista de agências da CTT Express, vai direto para a CTT Express; nos demais códigos com ambos começando em `00`, o Peek consulta TIPSA, CTT Express e Austrian Post ao mesmo tempo, com a TIPSA primeiro. Se a loja informou TIPSA, toque em "Trocar" e selecione-a. Não é preciso código postal.

Um *albarán* de 10 dígitos não pode ser rastreado pela TIPSA, nem usando "Trocar" nem por link. O Peek não sugere nem consulta a TIPSA para 10 dígitos; se você selecionar TIPSA manualmente, o Peek avisa que ela "não emite códigos como este" e recorre aos serviços de rastreamento. Procure o código completo de 22 dígitos ou o link de rastreamento no e-mail ou SMS da loja, ou peça ao remetente. Links de tip-sa.com vindos do e-mail da loja funcionam quando trazem `id=` em páginas terminadas em `datos_env.php`, `datos_prestashop.php` ou `datos.php`, ou no endereço raiz de tip-sa.com. Links do localizador da TIPSA ou de páginas em tipsa-dinapaq.com não são lidos.

O Peek mostra o status, o histórico com cada cidade no mapa, o nome do remetente (salvo se a TIPSA o mascarar) e o peso. Os status ficam no espanhol original da TIPSA, e o Peek resume cada etapa no seu idioma: `PENDIENTE DE ENTREGAR A TIPSA` aparece como "Etiqueta criada"; `REPARTO` como "Saiu para entrega"; `Ausente`, `Cerrado` e `Faltan Datos / Datos incorrectos` como "Tentativa de entrega"; `DISPONIBLE` e `Pendiente de recoger en Tipsa` como "Pronta para retirada"; `Devolver a Origen` como "Precisa de atenção"; `ENTREGADO` como "Entregue"; e as demais leituras como "Em trânsito". O Peek interpreta todos os horários no fuso de Madri (inclusive de agências portuguesas) e converte para o seu fuso horário; em peektracker.com e nos apps de iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome do fuso. Ele descarta destinatário, endereço, códigos postais, referência, quantidade de volumes, cubagem e comprovante de entrega, não exibe previsão de entrega nem acompanha troca de transportadora. Enquanto o pacote está aberto no Peek, ou no dia da entrega com alertas ligados, e o rastreador oficial da TIPSA responde, ele verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6h de Madri no dia em que a TIPSA marca saída para entrega; fora disso, de hora em hora. Quando só os serviços de rastreamento respondem, verifica de hora em hora, a cada 3 horas depois de um dia sem nova movimentação, e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Ele pode avisar sempre que o status muda. Após dois dias sem novidades (ou 12 horas ainda em "Etiqueta criada" ou "Recebida pela transportadora"), o Peek verifica de hora em hora.

## Perguntas frequentes sobre rastreamento TIPSA

### O que é a TIPSA na Espanha?

TIPSA significa Transporte Integral de Paquetería, uma empresa de encomendas expressas do grupo Geopost. Sua página institucional cita mais de 330 agências (a página inicial em Portugal cita mais de 300), além de 13 hubs — entre eles Madri, Barcelona e Valência — e entregas internacionais via DPDgroup.

### Quanto tempo a TIPSA leva para entregar?

Depende do serviço contratado pela loja. A TIPSA oferece entregas em 24 horas (uma tentativa no dia útil seguinte) ou em até 48 horas; em Portugal, 24 horas para as principais cidades e 24 a 48 horas nas demais regiões.

### Posso rastrear um pacote da TIPSA com 10 dígitos?

Não: o localizador da TIPSA exige o código completo de 22 dígitos, e o Peek também não trata 10 dígitos como código TIPSA. Esses 10 dígitos são apenas o *albarán* final; peça o código completo ao remetente.

### A TIPSA tem pontos de retirada?

Sim, pontos Pickup em lojas parceiras e lockers, compartilhados com a SEUR, com prazo de retirada de 7 dias. Essa opção só está disponível quando a loja a oferece no envio.

:::sources
- [TIPSA: Delivery locator](https://www.tip-sa.com/en/delivery-locator) – código de 22 dígitos, formulário e busca por referência com identificador (agência + cliente)
- [TIPSA: Frequently Asked Questions](https://www.tip-sa.com/en/recursos/faqs) – código ausente, guarda de 15 dias, documento, mudança de endereço, extravio/avaria, telefones e e-mail
- [TIPSA: Contact](https://www.tip-sa.com/en/contact) – telefones e motivos do formulário
- [TIPSA: Collection points](https://www.tip-sa.com/en/services/collection-points) – rede Pickup
- [TIPSA: Pre-alert](https://www.tip-sa.com/en/services/pre-alert) – aviso por SMS/e-mail e prazos em Portugal
- [TIPSA: TIPSA APP](https://www.tip-sa.com/en/recursos/app) – página do aplicativo
- [TIPSA: Consulta de Envíos](https://dinapaqweb.tipsa-dinapaq.com/dinapaqweb/detalle_envio.php) – página em espanhol, status e agência de destino
- [TIPSA: Localizar mi envío](https://www.tip-sa.com/es/localizacion-envios) – localizador em espanhol e telefone no rodapé
- [TIPSA: Preguntas frecuentes](https://www.tip-sa.com/es/recursos/faqs) – cobertura e linha 900
- [TIPSA: Empresa de transporte urgente](https://www.tip-sa.com/es/tipsa) – Geopost, 330 agências, hubs e lockers
- [TIPSA: Página inicial (Portugal)](https://www.tip-sa.com/pt) – mais de 300 agências
- [TIPSA: FAQs eTIPSA](https://www.tip-sa.com/es/faqs-etipsa) – duas tentativas, 24h e 48h, janela no eTIPSA Premium, sábados e avisos da loja
- [TIPSA: 360º eCommerce](https://www.tip-sa.com/es/envios-de-paquetes/360o-ecommerce) – três tentativas no Premium
- [TIPSA: Recogida en puntos de recogida](https://www.tip-sa.com/es/envios-de-paquetes/puntos-de-recogidas) – rede compartilhada com a SEUR, 7 dias e dias de abertura
- [TIPSA: Agencias de transporte](https://www.tip-sa.com/es/agencias-de-transporte) – buscador de agências
- [TIPSA: Puntos Pickup](https://aplicaciones.tip-sa.com/puntospickup/) – buscador de pontos Pickup
- [TIPSA: Contrarreembolso](https://www.tip-sa.com/es/envios-de-paquetes/reembolso) – cobrança na entrega até €1.000 por remessa
- [TIPSA: Portes debidos](https://www.tip-sa.com/es/envios-de-paquetes/portes-debidos) – frete cobrado no destino
- [TIPSA: Aviso legal](https://www.tip-sa.com/es/tipsa-aviso-legal) – razão social
- [TIPSA: Contacto (Portugal)](https://www.tip-sa.com/pt/contacto) – mesmos telefones para Portugal
:::

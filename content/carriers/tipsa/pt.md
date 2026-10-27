---
title: TIPSA rastreio: número, estados, agências e contactos
description: Rastreio TIPSA em Portugal: o número de 22 algarismos, os estados em espanhol explicados, entregas falhadas, levantamento na agência e contactos.
slug: tipsa-rastreio
published: 2026-10-26
updated: 2026-10-27
---

A TIPSA (Transporte Integral de Paquetería) é uma transportadora espanhola de encomendas urgentes, da rede Geopost, com agências em Espanha, Portugal e Andorra. Para seguir a encomenda, escreve o número de 22 algarismos no [localizador de envios](https://www.tip-sa.com/pt/localizador-envios) do site português da TIPSA ou cola-o na caixa acima. Quem te dá o número é o remetente, normalmente a loja onde compraste.

## Número de rastreio TIPSA: 22 algarismos

O localizador pede o «número de envio composto por 22 dígitos». O número junta dois códigos de agência e o albarán, o nome espanhol da guia do envio:

:::anatomy 010000 010000 0000000000
- 010000 | Primeiro código de agência: uma agência TIPSA ligada ao envio
- 010000 | Segundo código de agência: a agência de origem
- 0000000000 | Albarán: o número do próprio envio
:::

| Número (inventado) | O que é |
| --- | --- |
| `0100000100000000000000` | Os 22 algarismos completos. É este que serve. |
| `010000 010000 0000000000` | O mesmo número com espaços. O Peek lê-o assim; no localizador da TIPSA, cujo campo só leva 22 carateres, escreve-o sem espaços. |
| `0000000000` | Só o albarán, com 10 algarismos. O localizador pede os 22. |

Não tens número? A FAQ da TIPSA manda pedi-lo ao remetente ou à delegação que faz a entrega, que encontras escrevendo a tua morada no [localizador de agências](https://www.tip-sa.com/pt/agencias-de-transporte). Também podes ligar para a TIPSA ou escrever para info@tip-sa.com. Mais sítios onde procurar em [onde encontrar o número de seguimento](guide:find-tracking-number).

Os 22 algarismos também têm a forma dos números da Austrian Post, da USPS e da Estafeta, e um número em que os dois códigos de agência começam por `00` tem a forma de um número da [CTT Express](carrier:ctt-express), outra transportadora espanhola. Confirma no email da loja qual é a transportadora.

## Estados TIPSA: o que querem dizer

O Peek lê o seguimento da TIPSA em espanhol e mostra os estados tal como a TIPSA os escreve. Estes são os que já registou, pela ordem em que costumam aparecer:

| Estado TIPSA | O que quer dizer para ti |
| --- | --- |
| `PENDIENTE DE ENTREGAR A TIPSA` | A loja registou o envio, mas a TIPSA ainda não tem a encomenda: é o estado de [etiqueta criada](guide:tracking-statuses). |
| `TRANSITO` | A caminho entre agências e plataformas da TIPSA. |
| `LECTURA EN HUB BAILEN` | Lida numa plataforma de intercâmbio da TIPSA; o nome da plataforma muda (`LECTURA EN HUB BARBERA`, por exemplo). |
| `Pendiente de llegada` | A caminho entre plataformas, antes de chegar à agência que entrega. |
| `LEIDO EN DESTINO` | Lida no destino. |
| `LECTURA EN AGENCIA DESTINO` | Lida na agência que faz a entrega; o nome dela vem a seguir. |
| `REPARTO` | Saiu para entrega. |
| `Ausente` | O estafeta não te encontrou e não conseguiu entregar. |
| `Faltan Datos / Datos incorrectos` | Faltam dados na morada ou estão errados. Fica anotado na volta de entrega, antes de nova tentativa. |
| `Solicitada en otra dirección` | Foi pedida a entrega noutra morada e a encomenda é reencaminhada. |
| `DISPONIBLE` ou `Pendiente de recoger en Tipsa` | Depois de uma entrega falhada, ficou guardada na agência à tua espera. |
| `Devolver a Origen` | Vai voltar ao remetente. |
| `ENTREGADO` | Entregue. |

As 13 plataformas de intercâmbio da TIPSA ficam todas em Espanha, por isso o histórico de uma encomenda para Portugal pode mostrar nomes de cidades espanholas. Depois de um `Ausente` ou de `Faltan Datos / Datos incorrectos`, fala com a agência indicada em `LECTURA EN AGENCIA DESTINO`.

## Entrega TIPSA em Portugal: agência, pontos e entrega falhada

A TIPSA diz ter estrutura própria de distribuição em toda a Península Ibérica, Portugal incluído. As suas [agências em Portugal](https://www.tip-sa.com/pt/agencias-de-transporte/portugal) vão do Porto, Braga e Vila Real a Coimbra, Viseu, Leiria, Lisboa (Prior Velho), Setúbal, Évora e Faro.

**Entrega falhada.** As páginas portuguesas da TIPSA não dizem quantas tentativas faz em Portugal. Depois de um `Ausente`, a encomenda pode ficar na agência, com `DISPONIBLE` ou `Pendiente de recoger en Tipsa`; para a ires buscar ou saberes o que vem a seguir, fala com a delegação que faz a entrega. Vê também [entrega falhada](guide:missed-delivery).

**Levantar na delegação.** Se não fores estar em casa no dia previsto ou preferires ir buscá-la, a FAQ da TIPSA diz que a encomenda fica 15 dias na delegação mais próxima do teu código postal, a contar da saída do envio e não da chegada; depois, volta ao remetente. Leva o teu documento de identificação ou o passaporte; quem for por ti leva uma fotocópia do teu. Com o serviço RECOLHA NA AGÊNCIA, a loja manda a encomenda diretamente para uma delegação, onde a levanta quem ela autorizar.

**Pontos de recolha e cacifos.** A TIPSA diz que completa a entrega em casa com pontos de recolha e cacifos em Espanha, Portugal e no resto da Europa, com cacifos abertos 24 horas por dia, 7 dias por semana. Mas as páginas portuguesas não explicam como funcionam em Portugal: a página dos pontos de recolha descreve a rede espanhola, a do serviço PORTUGAL não os lista e o [localizador de pontos](https://aplicaciones.tip-sa.com/puntospickup/) está só em espanhol.

**Mudar a entrega.** Só o remetente pode autorizar uma mudança de morada ou dos dados do envio; se és o destinatário, pede-a à loja. Mais em [mudar a morada de entrega](guide:change-delivery-address).

**Pagar na entrega.** Com o CONTRA REEMBOLSO, a TIPSA cobra-te no destino, contra a entrega, o valor que a loja fixou, até 1000 euros por envio. Com PORTES A PAGAR, cobra-te o transporte, também no destino. A TIPSA não diz se pagas em dinheiro ou com cartão: pergunta à loja ou à delegação.

## Avisos da TIPSA e mensagens falsas

Se a loja contratar o PRÉ-ALERTA, a TIPSA avisa-te por SMS ou email do dia em que a encomenda chega ao destino e de como vai ser a entrega.

> A TIPSA não publica avisos sobre SMS ou emails falsos. Os únicos pagamentos que descreve para quem recebe são o CONTRA REEMBOLSO e os PORTES A PAGAR, cobrados no destino, contra a entrega. Uma mensagem «da TIPSA» que te manda pagar portes ou uma nova entrega através de uma ligação não corresponde a nada disto: não pagues e procura o número no localizador do tip-sa.com. Mais em [SMS falsos de entregas](guide:delivery-scam-texts).

## Encomenda TIPSA parada, perdida ou devolvida

As páginas da TIPSA não falam de um seguimento que deixa de avançar. Se o estado não muda há dias, fala com a delegação que faz a entrega ou com a loja; [encomenda parada](guide:tracking-not-updating) explica quando te deves preocupar.

**Perda ou danos.** A FAQ da TIPSA só diz isto: contacta o remetente e informa-o. A TIPSA não publica procedimento nem prazos de reclamação para quem recebe: é a loja, que contratou o transporte, quem trata do assunto com a TIPSA. O mesmo vale para um `ENTREGADO` que não recebeste. Vê [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [entregue mas não recebida](guide:delivered-not-received).

**Devolvida.** Uma encomenda que ninguém levanta nos 15 dias em que fica na delegação volta ao remetente, e o seguimento pode mostrar `Devolver a Origen`. Combina com a loja um novo envio ou o reembolso; mais em [encomenda devolvida ao remetente](guide:returned-to-sender).

**Devolver uma compra.** Numa compra à distância podes desistir em 14 dias seguidos, sem encargos nem justificação, ou em 12 meses se a loja não te informou desse direito, segundo a Direção-Geral do Consumidor. A loja diz-te como devolver; para enviares tu, a FAQ da TIPSA indica a MANDALOXTIPSA ou uma delegação.

## Reclamar em Portugal: Livro de Reclamações e arbitragem

Tenta primeiro resolver com a TIPSA ou com a loja: o Livro de Reclamações diz que não é obrigatório, mas que deves tentar sempre uma solução amigável.

- **Livro de Reclamações.** O livro físico é obrigatório nos estabelecimentos fixos com atendimento ao público; o eletrónico, em [livroreclamacoes.pt](https://www.livroreclamacoes.pt/Inicio/), também nas empresas com atividade através de um site. Reclamar é grátis e basta um email válido, sem registo. A empresa tem 15 dias úteis para te responder por email, e a reclamação segue também para a entidade reguladora. O site português da TIPSA não tem ligação para o livro; se a TIPSA não estiver na plataforma, reclama no livro físico ou diretamente à entidade reguladora ou fiscalizadora.
- **Arbitragem.** Os centros de arbitragem de consumo são gratuitos ou de custo muito reduzido, resolvem em até 90 dias, aceitam reclamações online e dispensam advogado, diz a Direção-Geral do Consumidor. Mas, fora dos setores em que a lei a impõe, a empresa só fica obrigada à arbitragem se tiver aderido a um centro ou se concordar com ela nesse conflito. O [CNIACC](https://www.cniacc.pt/pt), de âmbito nacional, atua onde os outros centros não têm competência, com mediação, conciliação e arbitragem.

## Encomendas de fora da UE: alfândega

As páginas da TIPSA não falam de alfândega nem de quem te cobra os impostos. As regras gerais:

- Desde 1 de julho de 2026 acabou a isenção de direitos aduaneiros para remessas até 150 €: numa compra online a uma empresa, pagas 3 € por adição, além do IVA, devido desde julho de 2021. No exemplo da Autoridade Tributária, uma remessa de 100 € com uma camisa e um chapéu tem duas adições: 6 €. Acima de 150 €, aplica-se a taxa de direitos de cada mercadoria.
- A partir de 1 de novembro de 2026, a UE vai aplicar também uma taxa de tratamento de 2 € às pequenas encomendas importadas, segundo a Comissão Europeia.

Mais em [alfândega e taxas](guide:customs).

## Contactos TIPSA em Portugal

Como as páginas portuguesas da TIPSA os indicavam a 26 de outubro de 2026:

- **Telefone:** 902 10 10 47 ou 900 10 10 47. A TIPSA não indica horário nem custo da chamada.
- **Formulário** em [tip-sa.com/pt/contacto](https://www.tip-sa.com/pt/contacto), com o motivo «Tenho um problema com o meu envio» e o «Número da remessa».
- **Email:** info@tip-sa.com, que a FAQ indica a quem não tem o número.
- **A delegação que faz a entrega**, que encontras pela tua morada no localizador de agências.

## Rastrear uma encomenda TIPSA com o Peek

Cola os 22 algarismos, com ou sem espaços, e o [Peek](/) consulta ao mesmo tempo a TIPSA e a Austrian Post: vês «a consultar …» e depois «TIPSA tem esta encomenda.» quando a TIPSA a conhece. Se os dois códigos de agência começarem por `00`, o Peek toma o número pela CTT Express; se a loja disse TIPSA, carrega em «Alterar» e escolhe a TIPSA. Com `00001000` no início, consulta a TIPSA, a CTT Express e a Austrian Post. Não precisas de código postal.

Um albarán de 10 algarismos não dá para seguir na TIPSA, nem com «Alterar» nem numa ligação. Uma ligação do tip-sa.com com `id=` (`datos_env.php`, `datos_prestashop.php` ou `datos.php`) serve; as do localizador ou do tipsa-dinapaq.com não.

O Peek mostra o estado, o histórico com a localidade de cada leitura no mapa, o remetente quando a TIPSA não o esconde com asteriscos e o «Peso». Lê todas as horas como hora de Madrid, também as das agências portuguesas. Não mostra o destinatário, a morada, a referência, o número de volumes, o comprovativo de entrega nem a data prevista de entrega, e não segue a encomenda se ela passar para outra transportadora.

Consulta a TIPSA de 10 em 10 minutos durante o dia, de 2 em 2 quando a encomenda está em `REPARTO` («Em distribuição») e de hora a hora ou de duas em duas horas à noite, e pode avisar-te de todas as atualizações, só das importantes ou só no dia da entrega. Com `DISPONIBLE` ou `Pendiente de recoger en Tipsa`, mostra «Pronta para levantamento». Não muda a entrega nem reclama por ti.

## Perguntas sobre o rastreio TIPSA

### O que é a TIPSA?

É a Transporte Integral de Paquetería, S.A., uma empresa de encomendas urgentes com sede em San Fernando de Henares (Madrid), da rede Geopost. A página «Sobre a TIPSA» fala de mais de 330 agências em Espanha, Portugal e Andorra, a página inicial de mais de 300. Para o estrangeiro, envia através do DPDgroup.

### Quanto tempo demora a TIPSA a entregar em Portugal?

Depende do serviço que a loja contratou. O serviço PORTUGAL entrega em 24 horas nas principais localidades e em 24 a 48 horas no resto do país.

### Posso seguir uma encomenda TIPSA com 10 algarismos?

Não. O localizador da TIPSA pede o número de 22 algarismos, e os 10 do fim são só o albarán. Se só tens esses, pede o número completo à loja.

### A TIPSA tem pontos de recolha em Portugal?

Diz que sim, com pontos e cacifos, mas não diz onde ficam nem quanto tempo lá espera a encomenda. Na delegação mais próxima do teu código postal, a encomenda fica 15 dias a contar da saída do envio.

:::sources
- [TIPSA: Rastrear o meu envio](https://www.tip-sa.com/pt/localizador-envios) – 22 algarismos, campo de 22 carateres
- [TIPSA: Perguntas Frequentes](https://www.tip-sa.com/pt/recursos/faqs) – número em falta, 15 dias, documentos, morada, perda, telefones, DPDgroup
- [TIPSA: Contacto](https://www.tip-sa.com/pt/contacto) – telefones, formulário
- [TIPSA: Página inicial](https://www.tip-sa.com/pt) – mais de 300 agências
- [TIPSA: Sobre a TIPSA](https://www.tip-sa.com/pt/tipsa) – Geopost, 330 agências, plataformas, cacifos
- [TIPSA: Portugal](https://www.tip-sa.com/pt/servicos/portugal) – prazos e serviços em Portugal
- [TIPSA: Pontos de recolhas](https://www.tip-sa.com/pt/servicos/pontos-recolhas) – rede de pontos em Espanha
- [TIPSA: Pré-alerta](https://www.tip-sa.com/pt/servicos/pre-alerta) – aviso por SMS e email
- [TIPSA: Contra reembolso](https://www.tip-sa.com/pt/servicos/reembolso) – até 1000 euros por envio
- [TIPSA: Portes a pagar](https://www.tip-sa.com/pt/servicos/portes-pagar) – transporte cobrado no destino
- [TIPSA: Recolha na agência](https://www.tip-sa.com/pt/servicos/recolha-na-agencia) – envio para uma delegação
- [TIPSA: Agências de transporte](https://www.tip-sa.com/pt/agencias-de-transporte) – localizador de agências
- [TIPSA: Agências de transporte em Portugal](https://www.tip-sa.com/pt/agencias-de-transporte/portugal) – agências em Portugal
- [TIPSA: Puntos Pickup](https://aplicaciones.tip-sa.com/puntospickup/) – localizador só em espanhol
- [TIPSA: Nota legal](https://www.tip-sa.com/pt/tipsa-nota-legal) – nome e sede
- [Livro de Reclamações: Perguntas frequentes](https://www.livroreclamacoes.pt/Inicio/PerguntasFrequentes) – livro físico e eletrónico, 15 dias úteis
- [Direção-Geral do Consumidor: Perguntas frequentes](https://www.consumidor.gov.pt/consumidor_4/perguntas-frequentes1) – arbitragem, desistência em 14 dias
- [CNIACC: Competências](https://www.cniacc.pt/pt/competencias-cniacc) – centro nacional supletivo
- [Portal das Finanças: Mercadorias eCommerce – Alterações para 1 de julho de 2026](https://info.portaldasfinancas.gov.pt/pt/destaques/Paginas/Mercadorias_eCommerce.aspx) – 3 € por adição, IVA
- [Comissão Europeia: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa de 2 € a partir de novembro
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê
:::

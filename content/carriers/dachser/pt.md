---
title: Dachser tracking: rastrear encomenda, estados e contactos
description: Rastrear uma encomenda Dachser em Portugal: a ligação de seguimento completa, o que querem dizer os estados, a marcação da entrega e os contactos.
slug: rastrear-encomenda-dachser
published: 2026-10-12
updated: 2026-10-27
---

A Dachser é um grupo alemão de logística cujas empresas em Portugal e Espanha transportam mercadoria e paletes, sobretudo entre empresas, e entregam encomendas volumosas em casa quando a loja contrata o serviço targo on-site. Para rastrear a encomenda, abre a ligação de seguimento do e-mail ou da mensagem que recebeste sobre a entrega: leva à página Customer Iberia da Dachser. Também a podes colar inteira na caixa acima.

## Número e ligação de seguimento Dachser

A Dachser não publica o formato dos seus números. A página Customer Iberia mostra um número de expedição e um «Número Único», que é o número que aparece na ligação. As páginas da Dachser dizem que pode ser criada uma ligação de seguimento para cada remessa e reencaminhada aos clientes, mas não dizem como te chega. Não recebeste nada? Pergunta à loja e vê [onde encontrar o número de seguimento](guide:find-tracking-number).

O que conta é a ligação. Tem este aspeto (número e chave inventados):

`https://customeriberia.dachser.com/customerarea/utilidades/seguimiento-publico/detalle?numeroUnico=00000000000&hash=EXAMPLE123`

Além do número, leva uma chave de acesso: `hash=`, ou `clave=` com um `fecha=` de oito algarismos. Sem a chave, a página não mostra nada. Copia a ligação inteira, tal como está.

Escrito sozinho, um número Dachser parece-se com os de muitas outras transportadoras (algarismos inventados):

| Número | Com que se parece |
| --- | --- |
| `1234567890` | 10 algarismos: [DHL](carrier:dhl-express), Mondial Relay, TIPSA e outras. |
| `12345678901` | 11 algarismos: GLS e outras. |
| `123456789012` | 12 algarismos: [FedEx](carrier:fedex), MRW, BRT e outras. |
| `1234567890123` | 13 algarismos: La Poste, DPD France e outras. |
| `12345678901234` | 14 algarismos: SEUR, DPD e outras. |

Se a mensagem fala da Dachser, confia nela e não no comprimento do número.

## Estados Dachser: o que quer dizer cada um

A página Customer Iberia tem os rótulos em português, espanhol, inglês e francês, mas o texto de cada passo vem do sistema da Dachser para essa remessa. A tabela do histórico chama-se «Histórico de Incidências», mas mostra todos os passos, não só os problemas.

O botão «Track & Trace» do dachser.pt abre outra ferramenta, o shipmentpointer do grupo. A ajuda da Dachser explica os estados dele com estas palavras; a página da tua ligação pode descrever os passos de outra forma:

| Palavras da Dachser | O que quer dizer para ti |
| --- | --- |
| `A remessa encontra-se em processamento.` | Está num armazém da Dachser, a ser triada e transferida. |
| `A remessa encontra-se em corrida principal.` | Vai no trajeto longo entre armazéns. |
| `A remessa está a ser entregue ao transportador local.` | Saiu para entrega com o veículo da zona. |
| `A remessa ainda não está a ser entregue ou não pôde ser entregue com sucesso.` | Está retida, ou uma entrega falhou. |
| `A remessa foi entregue.` | Entregue. |
| `A remessa foi "reservada" (por exemplo, cancelamento).` | Cancelada. |

Os que confundem:

- «Em processamento» quer dizer um armazém da Dachser, não outra empresa.
- «Corrida principal» é o transporte de longa distância entre o armazém de partida e o que faz a entrega.
- «A ser entregue ao transportador local» parece uma passagem para outra empresa, mas é o texto da Dachser para a encomenda que saiu para entrega.
- «Ainda não está a ser entregue ou não pôde ser entregue» cobre vários casos. A ajuda espanhola da Dachser dá três exemplos: o destinatário pediu aviso antes da entrega, a mercadoria foi recusada ou a morada estava errada. Confirma que a loja tem o teu telefone e a tua morada certos.
- «Reservada» quer dizer cancelada, não marcada.

## Entrega Dachser: marcação, sábados e ausência

**Marcação.** A Dachser descreve o targo on-site como um serviço para envios a particulares entregues sempre com marcação prévia com o destinatário, de manhã ou à tarde, pelo motorista no passeio à porta. Com o targo on-site plus, a entrega é feita no local de utilização. Com o targo on-site fix, combinas com a loja uma data de entrega fixa quando compras. As condições espanholas da Dachser acrescentam que o targo on-site não tem prazo de entrega e que, em geral, a entrega é combinada por telefone com o destinatário: conta com uma chamada.

**Escolher a data.** A página de algumas remessas pode mostrar «Concertar data de entrega», com um «Dia de entrega» e uma «Janela horário» para escolher e uma confirmação. Se não vires essa opção, espera pela chamada da Dachser ou fala com a loja.

**Dias e prazos.** Nas condições espanholas, a Dachser não é obrigada a entregar aos domingos nem feriados, e ao sábado só entrega com os serviços targospeed 10 e targofix 10, combinados com a delegação. Nas condições portuguesas, os prazos de entrega são meramente indicativos: contam a partir do dia útil seguinte ao da recolha da mercadoria, sem sábados, domingos e feriados.

**Sem pontos de recolha nem cacifos.** As páginas portuguesas e espanholas da Dachser não falam de pontos de recolha nem de cacifos para destinatários, nem de aviso de passagem, número de tentativas ou prazo de espera.

**Se não estiveres.** Nas condições da Dachser em Portugal, uma entrega que falha por ausência do destinatário, por morada incorreta ou incompleta ou por recusa da mercadoria é da responsabilidade do ordenante, que paga as despesas. A segunda entrega e a marcação com o destinatário são serviços extra pagos por quem envia. Se falhaste a entrega, avisa a loja depressa.

## Encomenda Dachser atrasada, danificada ou perdida

Compraste à loja, e é a loja ou quem envia que tem o contrato com a Dachser: fala primeiro com eles sobre atrasos, perdas e reembolsos.

**Sem novidades.** Vê se tens uma chamada perdida da Dachser e lê [encomenda parada](guide:tracking-not-updating).

**Danos.** As condições da Dachser pedem que a perda ou o dano seja descrito por escrito no momento da entrega, por isso vê a mercadoria antes de o motorista sair e anota os danos nos documentos de entrega. Estes prazos estão no contrato entre a Dachser e o seu cliente, em Portugal e em Espanha:

- **Danos não aparentes:** 7 dias corridos após a entrega, no transporte nacional; no internacional, segundo as condições espanholas, 7 dias sem contar domingos e feriados.
- **Atraso:** reservas por escrito no prazo de 21 dias, a contar do dia seguinte ao da entrega (transporte nacional).

Guarda os volumes na embalagem original, à disposição da Dachser, e avisa a loja no mesmo dia.

**Dada como entregue mas não chegou.** Pede à loja que veja o comprovativo de entrega e lê [encomenda dada como entregue mas não recebi](guide:delivered-not-received).

## Dachser contactos em Portugal

A Dachser não publica uma linha de apoio para destinatários. Estes são os contactos que as páginas portuguesa e espanhola da Dachser indicavam a 12 de outubro de 2026:

- **Portugal:** DACHSER Portugal, S.U. Lda, Rua Agra Nova 120, Aveleda, 4485-040 Vila do Conde. Telefone +351 221 210 500 (chamada para a rede fixa nacional), e-mail dachser-spain.iberia@dachser.com. Não há horário publicado.
- **Formulário:** o dachser.pt tem uma página de contacto com formulário.
- **Reclamações:** o dachser.pt liga ao Livro de Reclamações oficial.
- **Espanha:** DACHSER Spain, S.A., sede regional de Madrid, em Coslada: +34 916 60 65 78, o mesmo e-mail. Sem horário nem custo da chamada.
- **Alemanha:** DACHSER SE, Kempten: +49 831 5916 0, info@dachser.com.

## Seguir encomenda Dachser com o Peek

O [Peek](/) não reconhece um número Dachser pelos algarismos nem o consegue procurar sem a chave, por isso cola a ligação completa da tua mensagem: o Peek reconhece-a como da Dachser e guarda-a. Se escreveste o número, toca em «Alterar», escolhe Dachser e cola a ligação no campo «Ligação de seguimento completa». Uma ligação com outro endereço, sem chave, com um parâmetro desconhecido ou de outro número é recusada com «Usa a ligação de seguimento completa do e-mail de envio.»

O Peek só lê a página Customer Iberia, por isso cobre as entregas da Dachser em Portugal e Espanha. Mostra o estado, o histórico e o dia de entrega previsto até à entrega. Cada passo aparece com as palavras do próprio Peek, como «Dados da encomenda recebidos», «Encomenda recolhida», «Encomenda chegou a um centro Dachser», «Em distribuição», «Agendamento de entrega atualizado» ou «Entregue»; o texto da Dachser não é guardado. As horas são lidas no fuso horário que trazem ou, quando não o têm, como hora de Madrid, e mostradas no fuso horário do teu dispositivo. A Dachser não indica locais, por isso os passos não aparecem no mapa. O remetente, o teu nome, a morada, os contactos e a assinatura ficam de fora.

O Peek verifica de 10 em 10 minutos durante o dia, de 2 em 2 quando a encomenda sai para entrega e de hora a hora ou de duas em duas horas à noite, e pode enviar-te notificações em «Todas as leituras», «Passos importantes» ou «Só no dia da entrega». Uma remessa desconhecida ou uma ligação estragada pode aparecer como não encontrada ou como uma verificação falhada.

## Perguntas sobre o Dachser tracking

### Quanto tempo demora uma entrega Dachser?

A Dachser não fixa um prazo para ti: nas condições portuguesas, os prazos são indicativos e contam a partir do dia útil seguinte à recolha, sem fins de semana nem feriados. No targo on-site, a data é combinada contigo, normalmente por telefone.

### A Dachser entrega ao sábado?

Só em alguns serviços. Segundo as condições espanholas, a entrega ao sábado é possível com o targospeed 10 e o targofix 10, combinada com a delegação, e o targo on-site plus pode entregar ao sábado ou à noite em certos países da rede.

### Onde fica a Dachser em Portugal?

A DACHSER Portugal está na Rua Agra Nova 120, em Aveleda, Vila do Conde. O grupo DACHSER SE é uma empresa familiar fundada por Thomas Dachser em 1930, com sede em Kempten, na Alemanha.

### Posso mudar a data de entrega da Dachser?

Às vezes. Se a página Customer Iberia mostrar «Concertar data de entrega», escolhe ali o dia e a janela horária; se não, atende a chamada da Dachser ou fala com a loja.

:::sources
- [DACHSER Portugal: Condições gerais de transporte](https://www.dachser.pt/downloads/Portugal/Portugal_Terms_Conditions.zip) – prazos indicativos, entrega falhada, serviços extra, reclamações em 7 e 21 dias
- [DACHSER Portugal: DACHSER disponibiliza serviço entargo em Portugal e Espanha](https://www.dachser.pt/pt/mediaroom/DACHSER-disponibiliza-servico-entargo-em-Portugal-e-Espanha-16917) – targo on-site com marcação prévia, manhã e tarde, passeio, local de utilização
- [DACHSER Portugal: Aviso legal](https://www.dachser.pt/pt/aviso-legal-273.php) – empresa, morada em Vila do Conde, telefone, e-mail
- [DACHSER Portugal: Contacto](https://www.dachser.pt/pt/contacto-275) – formulário de contacto
- [DACHSER Portugal: página inicial](https://www.dachser.pt/pt/) – Track & Trace abre o shipmentpointer, Livro de Reclamações
- [Dachser Customer Iberia: textos da página em português](https://customeriberia.dachser.com/customerarea/assets/i18n/common/pt.json) – Número Único, Histórico de Incidências, Concertar data de entrega
- [Dachser Customer Iberia: página pública da remessa](https://customeriberia.dachser.com/customerarea/utilidades/seguimiento-publico/detalle) – a página que a ligação abre
- [Dachser: ajuda do shipmentpointer](https://elogistics.dachser.com/help/pt/shp.html) – textos dos estados em português, ligação de rastreio por remessa
- [Dachser: shipmentpointer, ayuda](https://elogistics.dachser.com/help/es/shp.html) – exemplos do estado de entrega retida ou falhada
- [Dachser Customer Iberia: textos da página em espanhol](https://customeriberia.dachser.com/customerarea/assets/i18n/common/es.json) – N.º Expedición, idiomas da página
- [DACHSER Spain: Condiciones generales European Logistics](https://www.dachser.es/downloads/Spain/Spain_Terms_Conditions.zip) – marcação por telefone, domingos, sábados, transporte internacional
- [DACHSER Spain: targo on-site fix permite fijar una fecha de entrega al realizar un pedido](https://www.dachser.es/es/mediaroom/DACHSER-amplia-su-cartera-de-servicios-para-entregas-B2C-24216) – targo on-site fix e plus
- [DACHSER Spain: Información legal](https://www.dachser.es/es/informacion-legal-273.php) – Coslada, telefone, DACHSER SE
- [Dachser: Company](https://www.dachser.com/en/company-10.php) – Kempten, 1930, empresa familiar
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê e mostra
:::

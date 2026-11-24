---
title: Rastreio YDH: códigos YDH...YQ e AZ, entregas e ajuda
description: Segue uma encomenda transfronteiriça YDH Express da China: códigos YDH...YQ e AZ, códigos DSD e CARR, parceiro local, alfândega e contactos.
slug: rastreio-ydh
published: 2026-11-24
updated: 2026-11-24
---

A YDH (*YDH Express* / *YDH Cross Border*, em chinês *义达跨境*) é uma operadora chinesa de logística transfronteiriça para comércio eletrónico fundada em 2005 e sediada em Xangai. Servindo mais de 20 centros chineses de comércio eletrónico — incluindo Shenzhen, Cantão, Hangzhou, Ningbo e Hong Kong — com escritórios no Japão, Estados Unidos, Reino Unido e Bélgica, a YDH movimenta pequenas encomendas e carga comercial para mais de 200 países e regiões antes de as entregar a parceiros locais. Podes consultar um envio no [portal de rastreio da YDH](https://track.ydhex.com/track_query.aspx), ou colar o código na caixa acima.

## Números de rastreio YDH: formatos YQ e AZ

Procura o número de guia nos detalhes da encomenda no marketplace ou no e-mail de expedição. Os números de rastreio YDH têm 15 caracteres, começam por `YDH`, seguem-se 10 dígitos e terminam em `YQ` ou `AZ`:

| Formato (inventado) | O que é |
| --- | --- |
| `YDH4829173640YQ` | Número de encomenda transfronteiriça YDH: `YDH` + 10 dígitos + `YQ`. |
| `YDH4829173640AZ` | Número de encomenda transfronteiriça YDH: `YDH` + 10 dígitos + `AZ`. |

Assim que a encomenda é desalfandegada no país de destino, a YDH entrega-a a uma transportadora local: [USPS](carrier:usps) ou [GOFO](carrier:gofo) nos Estados Unidos e [Poste Italiane](carrier:poste-italiane) em Itália estão entre aquelas cujos registos a YDH retransmite sob o teu número `YDH`. A YDH também pode fornecer o número próprio da transportadora local. Para outros padrões internacionais, consulta [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados e códigos YDH

A YDH anexa códigos curtos aos seus registos de armazém, exportação e voo (em chinês ou inglês) e retransmite também os registos do parceiro de destino no texto original desse parceiro.

| Código / texto YDH | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `IR` (`Shipment information received`, `Label Created`) | Registo eletrónico recebido do vendedor antes de a YDH registar a encomenda física. | «Etiqueta criada» |
| `AF` (`Shipment arrived at facility`), `GNZY` (`快件到达作业中心`) | Encomenda chegada ao armazém ou centro de triagem da YDH na China. | «Em trânsito» |
| `HBYP`, `DF`, `DA` (`Shipment departed from airport` / `航班起飞`), `TDDY` (`Flight delay`), `AA` (`Shipment arrived at airport` / `航班到达`) | Em fila para embarque aéreo, partiu da China (ou sofreu atraso de voo) e aterrou no aeroporto de destino. | «Em trânsito» |
| `RC` (`Released from customs`), `CARR` (`handed over to the final carrier`), `DSD` (`D-SCAN DELAY`), `PU` | Desalfandegada no destino, entregue à transportadora local, à espera do primeiro registo desse parceiro (`D-SCAN DELAY`) ou recolhida por ele. | «Em trânsito» |
| `OD` (`Out for Delivery`) | Carregada pela transportadora de destino para entrega final. | «Em distribuição» |
| `DD` | Ocorrência de entrega ou tentativa falhada comunicada pelo parceiro local. | «Tentativa de entrega» / «Problema reportado» |
| `CC` | Entregue pelo parceiro de destino (com nome do signatário quando fornecido). | «Entregue» |

**O que significa `D-SCAN DELAY` (`DSD`).** Quando a YDH entrega um lote consolidado a um operador postal ou transportadora local (`CARR`), podem passar vários dias até esse parceiro registar individualmente a encomenda na sua rede. `D-SCAN DELAY` assinala esse atraso de passagem — a encomenda continua a caminho e não está perdida.

## Como funciona a entrega transfronteiriça da YDH

Em [ydhex.com](https://www.ydhex.com/), a YDH indica mais de 50 companhias aéreas e marítimas reservadas e desalfandegamento em 12 países. A sua [página de contactos](https://www.ydhex.com/product/59/) lista escritórios no Japão (Tóquio, Narita, Osaka, Kansai e Nagoya), Estados Unidos (La Puente, Califórnia), Reino Unido (Hounslow) e Bélgica (Grâce-Hollogne, perto de Liège).

**Entrega no último quilómetro.** A YDH trata da recolha de exportação, transporte aéreo e desalfandegamento, transferindo depois a encomenda (`CARR`) para os correios ou transportadora local do teu país para entrega à porta, ponto de levantamento ou devoluções. As tentativas, locais seguros e prazos de guarda dependem da transportadora local.

**Alfândega e encargos de importação.** Pedidos transfronteiriços enviados pela YDH ficam sujeitos ao IVA de importação, direitos aduaneiros e taxas locais do teu país, salvo pré-pagamento no vendedor ou marketplace. Consulta [encomendas retidas na alfândega](guide:customs).

## Encomenda YDH parada, sem novidades ou não entregue

Como o contrato de transporte da YDH é com o comerciante ou marketplace e não com o comprador, contacta sempre primeiro o vendedor ou abre pedido na plataforma para atrasos, correções de morada ou reembolsos.

- **Pausas durante o voo ou passagem local:** silêncios de vários dias são habituais enquanto a encomenda aguarda voo (`HBYP`), desalfandegamento (`RC`) ou o primeiro registo local (`DSD` / `D-SCAN DELAY`). Consulta [porque o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Usa o número local após a entrega ao parceiro:** assim que a YDH mostrar o número próprio da transportadora local, o site desse operador pode oferecer opções de entrega que a YDH não disponibiliza.
- **Entregue, danificada ou perdida:** se mostrar `CC` / entregue sem a encomenda ter chegado, confirma primeiro com familiares, vizinhos, portaria e transportadora local, e avisa logo o vendedor. Consulta [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos YDH

Contactos oficiais publicados na [página de contactos da YDH](https://www.ydhex.com/product/59/):

- **Linhas de apoio na China:** `800-988-1378` / `400-888-1378`.
- **Apoio ao cliente Xangai:** `(+86) 21-52277600` / `(+86) 21-52277688` (sede: 28/F, AFC Grand Hongqiao International, No. 999 Li'an Road, Minhang District, Xangai 201100, China).
- **Escritório de Hong Kong:** `+852 2690-1686`, `hk@ydhexpress.com`.
- **Escritório do Japão (Tóquio):** `+81 3-5644-5733`.
- **Escritório da Bélgica (Liège):** `+32 470 588 200`.

> Desconfia de SMS ou e-mails inesperados a pedir um pequeno pagamento por cartão para «desalfandegar» ou «atualizar a morada» de uma encomenda da China. Confirma qualquer taxa legítima na página do pedido do marketplace ou no site oficial dos correios do teu país. Consulta [SMS falsas de entregas](guide:delivery-scam-texts).

## Segue uma encomenda YDH com o Peek

O [Peek](/) reconhece automaticamente `YDH` + 10 dígitos + `YQ` ou `AZ` (`YDH4829173640YQ` e `YDH4829173640AZ`, dígitos inventados) como YDH com elevada confiança, e também links colados `track.ydhex.com/track_query.aspx?track_number=`. Se precisares de escolher a YDH manualmente, toca em «Alterar» ao adicionar ou em «Alterar transportadora» na página da encomenda (pesquisar «YDH Express», «YDH Cross Border» ou «义达跨境» também a encontra).

O Peek lê diretamente o rastreio da YDH e mostra a etapa («Etiqueta criada», «Em trânsito», «Em distribuição», «Tentativa de entrega», «Problema reportado», «Entregue»), o texto de cada registo — da YDH ou da transportadora local — com o respetivo local, e o país de destino. Os códigos YDH como `CARR` e `DSD` definem a etapa; o Peek não os mostra. A YDH data os registos na China pela hora de Xangai e os da transportadora local pelo fuso horário desse local; o Peek apresenta-os na hora do teu dispositivo. Quando a YDH indica o número próprio da transportadora local e o Peek reconhece essa transportadora, o Peek verifica se ela tem a encomenda: a ficha mostra então «Entrega:» seguido do nome da transportadora e apresenta ambos os números. O nome do signatário fica fora de todas as aplicações Peek, e o Peek omite a referência de cliente e a etiqueta de parceiro da YDH. O rastreio da YDH não fornece data prevista, peso, nome do serviço, ponto de levantamento nem fotografia de entrega.

Enquanto manténs a encomenda aberta no Peek, ou no dia da entrega com as notificações ativas, o Peek verifica até de 10 em 10 minutos; no dia em que a YDH mostra `OD`, de 2 em 2 minutos a partir das 6 h pela hora do registo mais recente da YDH, enquanto o rastreio próprio da YDH responder. Nos restantes casos verifica de hora em hora. Após 12 horas só em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora em hora seja como for, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da YDH não responde e o Peek acompanha a encomenda apenas por serviços de rastreio, verifica de hora em hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia de distribuição enquanto a mantiveres aberta ou com notificações ativas.

Quando o último registo tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-o com a data desse registo, como «Sem atualizações desde seg., 12/10». A página mostra então: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a YDH.», com a ligação «Pede ajuda a YDH» para a página de contactos da YDH. Após três semanas sem novos registos, mostra: «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a YDH.»

Podes escolher os alertas «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek apenas acompanha o rastreio e não pode contactar a YDH, desalfandegar a encomenda nem redirecioná-la junto do parceiro local.

## Perguntas sobre o rastreio YDH

### O que é a YDH Express?

A YDH (*义达跨境*) é uma operadora de logística e-commerce transfronteiriça sediada em Xangai fundada em 2005 que envia pequenas encomendas da China para mais de 200 países e as entrega a correios ou transportadoras locais para entrega final.

### O que significam os códigos `YDH...YQ` e `YDH...AZ`?

Ambos são formatos de guia YDH Express de 15 caracteres compostos por `YDH`, 10 dígitos e `YQ` ou `AZ` (por exemplo `YDH4829173640YQ` ou `YDH4829173640AZ`, dígitos inventados).

### O que significa `D-SCAN DELAY` (`DSD`) no rastreio YDH?

`D-SCAN DELAY` significa que a YDH transferiu a encomenda para a transportadora local do país de destino, mas esse parceiro ainda não publicou o primeiro registo de aceitação. O envio mantém-se em trânsito enquanto aguarda entrada local.

### Como contacto o apoio ao cliente da YDH?

Contacta primeiro o vendedor ou marketplace para reembolsos ou problemas de entrega. Para questões à transportadora, a YDH indica as linhas na China (`800-988-1378` / `400-888-1378`), Xangai (`+86 21-52277600` / `7688`), Hong Kong (`+852 2690-1686`, `hk@ydhexpress.com`), Japão (`+81 3-5644-5733`) e Bélgica (`+32 470 588 200`) em [ydhex.com/product/59/](https://www.ydhex.com/product/59/).

:::sources
- [YDH: Site oficial](https://www.ydhex.com/) – fundada em 2005, 20+ hubs na China, 200+ países/regiões, 50+ companhias aéreas e marítimas, desalfandegamento em 12 países
- [YDH: Contact Us](https://www.ydhex.com/product/59/) – sede em Xangai, linhas China (`800-988-1378`, `400-888-1378`, `+86 21-52277600`/`7688`), escritórios em Hong Kong, Japão, EUA, Reino Unido e Bélgica (URL de ajuda ligado pelo Peek)
- [YDH: Portal de rastreio](https://track.ydhex.com/track_query.aspx) – consulta pública de encomendas YDH
- [Universal Parcel Scraper: ydh](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ydh) – deteção `YDH` + 10 dígitos + `YQ`/`AZ`, regra de link `track.ydhex.com`, códigos (`IR`, `AF`, `DA`, `AA`, `RC`, `CARR`, `DSD`, `PU`, `OD`, `CC`, `DD`), número da transportadora local, fusos horários e URL de ajuda
:::

---
title: Seguimento J&T Express Australia: códigos JTAU e estados
description: Segue uma encomenda J&T Express Australia: formato JTAU de 12 algarismos, estados em inglês, foto de entrega, linha 13 18 18 e seguimento Peek.
slug: seguimento-jt-express-australia
published: 2026-11-21
updated: 2026-11-21
---

A J&T Express Australia (*J&T Express Australia Pty Ltd*, com sede social em Villawood, Nova Gales do Sul) é o ramo australiano do grupo J&T Express, presente no Sudeste Asiático e na China continental. Entrega encomendas na Austrália para plataformas de comércio eletrónico, marcas e retalhistas, incluindo compras vindas do estrangeiro depois de desalfandegadas. Os seus números começam por `JTAU`: cola o teu na caixa acima ou pesquisa-o no [localizador oficial da J&T Australia](https://www.jtexpress.au/tracking). A loja envia-te o código no e-mail de expedição ou na página da encomenda.

## Número de seguimento da J&T Express Australia: como é

Uma carta de porte (*waybill*) australiana da J&T tem `JTAU` seguido de 12 algarismos (16 caracteres ao todo), o único formato que o Peek conhece para esta rede (exemplos inventados):

| Número (inventado) | O que é |
| --- | --- |
| `JTAU482910573649` | `JTAU` e 12 algarismos: uma encomenda da J&T Express Australia, detetada de imediato pelo Peek. |
| `JTAU48291057364` | Falta um algarismo. Se nenhuma transportadora responder, `peektracker.com` mostra «Começa como um número de seguimento de J&T Express Australia, mas falta-lhe um carácter: pode estar mal escrito. Compara-o com a tua mensagem de envio.» |
| `JTDE482910573649` | `JTDE` e 12 algarismos: [J&T Express Alemanha](carrier:j-and-t-de). |
| `JTE482910573649` | `JTE` e 12 algarismos: [J&T Express Middle East](carrier:j-and-t-middle-east) (Arábia Saudita, EAU e Kuwait). `JTUK` (`JTUK482910573649`) pertence à J&T Express UK. |
| `JT4829105736491` | `JT` e 13 algarismos: redes da J&T no Sudeste Asiático e [J&T Express China](carrier:j-and-t-cn). |

Espaços, traços e minúsculas não afetam a leitura no Peek (`jtau 4829 1057 3649` funciona da mesma forma); o catálogo também reconhece os nomes *J&T Express (AU)*, *J&T Express AU*, *J&T Australia* e *JT Express Australia*.

**Onde encontrar.** A J&T recebe o teu nome, morada e contactos através do remetente ou marketplace: procura no e-mail de expedição ou nos detalhes da compra ([como encontrar o número de seguimento](guide:find-tracking-number)). Em `jtexpress.au/tracking` podes introduzir até 10 números separados por espaços ou vírgulas; se o número não existir, surge `No tracking records yet`.

## Estados de seguimento da J&T Express Australia explicados

O localizador da J&T apresenta cada leitura com uma hora e uma frase, sem indicar cidade nem armazém. O Peek pede as frases em inglês (alguns primeiros passos também existem em chinês no localizador: `包裹已从清关行取件。`, `快件已揽收成功，运输准备中。`, `快件到达转运中心，做发往下一站的运输准备`, `快件从转运中心发往下一站`):

| Estado da J&T | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `The parcel has been picked up from customs clearance.` | Encomenda vinda do estrangeiro: a alfândega já terminou e a J&T recolheu o pacote junto do despachante (primeira leitura de importação, não uma retenção aduaneira). | «Aceite pela transportadora» |
| `The parcel has been successfully picked up and is being prepared for transportation.` | A J&T recolheu o volume e prepara-o para transporte. | «Aceite pela transportadora» |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination.` | Chegou a um centro de distribuição da J&T rumo à etapa seguinte. | «Em trânsito» |
| `The parcel is dispatched from the transit center to the next destination` | Saiu de um centro de trânsito para a paragem seguinte. | «Em trânsito» |
| `Your parcel is out for delivery.` | Em volta de distribuição para a tua morada. | «Em distribuição» |
| `The building was closed when we arrived. We'll try again.` | O edifício estava fechado à chegada do estafeta; a J&T voltará a tentar. | «Tentativa de entrega» |
| `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.` | Falha na entrega por morada incorreta ou incompleta; a J&T pede atualização. | «Tentativa de entrega» |
| `Your parcel has been delivered.` | Entregue. | «Entregue» |

Mais detalhes sobre importações em [encomendas retidas na alfândega](guide:customs). Qualquer outra frase é classificada pelas suas palavras (como «Em trânsito», exceto se a encomenda já estiver em distribuição ou num passo posterior).

No localizador da J&T algumas frases terminam com o número de apoio ao cliente: o Peek retira esse trecho, o texto entre parênteses retos (`[…]`, `【…】`), números de telefone e nomes de motorista ou remetente. Quando a leitura de entrega indica quem assinou ou recebeu a encomenda, o Peek mantém esse nome para quem adicionou o pacote e oculta-o nas ligações partilhadas. Como as leituras não têm localidade, nem a J&T nem o Peek conseguem dizer em que estado ou centro está o volume.

## Entrega da J&T Express Australia e comprovativo de entrega

A J&T recruta empresas parceiras em toda a Austrália para recolha e última milha, sem transferência para outra transportadora com segundo número: o código `JTAU` acompanha a encomenda até à porta.

**Encomendas vindas de linhas internacionais.** Operadores internacionais podem entregar remessas em lote à J&T no porto de chegada: a [Yanwen](carrier:yanwen), por exemplo, passa depois a mostrar as frases da J&T no seu próprio histórico (onde o Peek apresenta a recolha da J&T como «Em trânsito», pois a Yanwen já trazia o pacote).

**Edifício fechado ou problema na morada.** Após `The building was closed when we arrived. We'll try again.`, a J&T faz nova tentativa; perante `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.`, confirma de imediato andar/fração e código postal com a loja ([alterar a morada de entrega](guide:change-delivery-address), [falha na entrega](guide:missed-delivery)). O site da J&T não indica quantas tentativas faz nem durante quanto tempo guarda encomendas, nem constam pontos de recolha ou cacifos nas leituras conhecidas.

**Comprovativo de entrega.** O aviso de privacidade da J&T descreve o registo feito na aplicação do motorista:
- fotografias da encomenda no local onde foi deixada, data e hora, geolocalização do dispositivo, assinatura quando exigida e notas de entrega;
- instrução aos motoristas para fotografarem o pacote evitando, sempre que possível, pessoas, matrículas e interiores de habitações;
- envio de uma ligação por SMS ou notificação eletrónica ao destinatário (qualquer pessoa com a ligação pode vê-la; não inclui nome nem contacto do motorista), bem como ao remetente ou marketplace;
- se não quiseres fotografia na entrega, podes enviar instruções através de `dataprotection@jtexpress.au`.

## Encomenda J&T parada, não encontrada ou entregue mas não recebida

Como a loja escolheu a J&T, numa compra online contacta primeiro o vendedor.

**Sem novas leituras há quatro dias.** Uma encomenda em «Aceite pela transportadora» ou «Em trânsito» sem novidades durante quatro dias recebe no Peek o aviso «Sem atualizações há quatro dias» e a ligação «Pede ajuda a J&T Express Australia» ([porque é que o seguimento não atualiza](guide:tracking-not-updating)).

**Ainda sem seguimento.** Um código que a J&T ainda não conhece mostra «À espera de J&T Express Australia». Se ao fim de dois dias nada tiver respondido, o Peek apresenta «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e passa a verificar diariamente e, após uma semana, semanalmente.

**Dada como entregue mas ausente, perdida ou danificada.** Abre a ligação da fotografia no SMS da J&T, verifica a entrada, familiares e vizinhos e avisa a loja e a J&T (que utiliza estes registos para averiguar pacotes extraviados, danificados ou entregues por engano, embora o site não defina indemnizações diretas para destinatários): pede reembolso ou reenvio à loja ou marketplace ([entregue mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel)).

## Apoio ao cliente e contactos da J&T Express Australia

Na [página de contactos da J&T Australia](https://www.jtexpress.au/contact) constam:

- **Telefone:** `13 18 18`, horário de funcionamento `08:00-20:00` (sem indicação de dias).
- **E-mail de apoio:** `service@jtexpress.au` (`business@jtexpress.au` e `partner@jtexpress.au` destinam-se a empresas e parceiros de entrega, e `dataprotection@jtexpress.au` à privacidade na sede `10/2 Christina Road, Villawood NSW 2163`).

O aviso de privacidade refere que os prestadores de centro de atendimento e apoio ao cliente estão na Malásia e nas Filipinas.

Um SMS da J&T com ligação para a foto de entrega pode ser verdadeiro; se porém pedir pagamentos ou dados pessoais, confirma o domínio `www.jtexpress.au` (`jtexpress.au`) e lê [mensagens falsas de entregas](guide:delivery-scam-texts).

## Seguir encomendas da J&T Express Australia com o Peek

O [Peek](/) identifica logo a J&T Express Australia a partir de um número `JTAU` («Transportadora detetada») ou de uma ligação `www.jtexpress.au/tracking` com `?q=` e um código `JTAU` (com outro número em `?q=`, lê esse número como se o tivesses colado isoladamente; se selecionares manualmente a J&T Australia para outro formato, indica que «não emite números como este»). Num código `JT` de 13 algarismos, o Peek consulta a J&T Express e serviços de seguimento.

O Peek interroga o localizador oficial em `UTC` (`GMT+0000`) e converte o momento exato de cada leitura para o fuso do teu dispositivo («Horas no teu fuso horário: …»), guardando até às 100 mais recentes, sem mapa, data prevista, peso, contactos de agência nem foto de entrega (acessível pela ligação no SMS). Se o localizador da J&T falhar ou ainda não conhecer o número, o Peek recorre a serviços de seguimento.

O Peek verifica até a cada 10 minutos com a encomenda aberta ou no dia da entrega com notificações ativas, e até a cada 2 minutos desde as `06:00` locais no dia em que a J&T regista «Em distribuição» enquanto o localizador da J&T responder (por serviços de seguimento: de hora a hora, a cada 3 h após um dia calmo e até a cada 15 minutos no dia da entrega com a encomenda aberta ou alertas ativos); nos restantes casos, e após 12 horas em «Aceite pela transportadora» ou dois dias sem leituras, de hora a hora («Todas as leituras», «Passos importantes» ou «Só a entrega»). O Peek não pode contactar a J&T ou a loja nem alterar a morada.

## Perguntas frequentes sobre o seguimento da J&T Express Australia

### A J&T Express Australia é fidedigna?

Sim. O *Australian Business Register* inscreve a *J&T EXPRESS AUSTRALIA PTY LTD* como sociedade privada australiana com ABN ativo desde 4 de junho de 2026 (`18 698 743 609`, ACN `698 743 609`, localização principal `NSW 2163`, sede registada em `10/2 Christina Road, Villawood NSW 2163`), integrada no grupo J&T Express (`01519.HK`) fundado na Indonésia em 2015.

### O que significa `picked up from customs clearance` no seguimento da J&T?

Que a alfândega ficou concluída: a J&T recolheu a encomenda importada junto do despachante aduaneiro para a entregar na Austrália. O Peek mostra «Aceite pela transportadora», não retenção aduaneira.

### Porque é que o meu número da J&T não é encontrado?

Confirma que começa por `JTAU` seguido de 12 algarismos (não `JTUK` nem `JTDE`). Se estiver certo, a J&T pode ainda não ter o pacote (`No tracking records yet`, «À espera de J&T Express Australia» no Peek); se continuar desconhecido após dois dias, fala com a loja.

### Como contactar a J&T Express Australia?

Liga para `13 18 18` (`08:00-20:00`) ou escreve para `service@jtexpress.au`, avisando também a loja onde fizeste a compra.

### A J&T Express Australia tira fotografia na entrega?

Sim: o aviso de privacidade indica que os motoristas fotografam a encomenda no local onde a deixam e recebes uma ligação para o comprovativo por SMS ou notificação eletrónica.

:::sources
- [J&T Express Australia: Track & Trace](https://www.jtexpress.au/tracking) – localizador oficial e mensagem `No tracking records yet`
- [J&T Express Australia: Contact Us](https://www.jtexpress.au/contact) – telefone 13 18 18 (08:00-20:00), service@jtexpress.au, business@jtexpress.au, partner@jtexpress.au e ABN 18 698 743 609
- [J&T Express Australia: Delivery Partners](https://www.jtexpress.au/career) – parceiros de recolha e entrega de última milha na Austrália
- [J&T Express Australia: About Us](https://www.jtexpress.au/about) – grupo J&T Express (01519.HK), fundado na Indonésia em 2015, Sudeste Asiático e China continental
- [J&T Express Australia: Privacy Notice](https://www.jtexpress.au/privacy-policy) – sede em 10/2 Christina Road, Villawood NSW 2163, fotos de entrega e ligação SMS, dataprotection@jtexpress.au, averiguações e suporte na Malásia e Filipinas
- [ABN Lookup: J&T Express Australia Pty Ltd](https://abr.business.gov.au/ABN/View?abn=18698743609) – ABN 18 698 743 609 ativo desde 4 de junho de 2026, empresa privada australiana, NSW 2163
- [Universal Parcel Scraper: J&T Express Australia](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – formato JTAU, frases em inglês e chinês, horas UTC, limpeza de menções, assinatura e página de ajuda
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de outras redes J&T, remessas em lote da Yanwen e serviços de seguimento de reserva
:::

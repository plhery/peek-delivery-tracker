---
title: J&T Express Alemanha rastreio: código JTDE e estados
description: Segue uma encomenda J&T Express na Alemanha: formato do código JTDE, o que quer dizer cada estado em inglês e o que fazer se atrasar, se perder ou não chegar.
slug: jt-express-alemanha-rastreio
published: 2026-11-16
updated: 2026-11-21
---

A J&T Express Germany (J&T Express Germany GmbH, Frankfurt am Main) é a rede alemã do grupo de entregas J&T Express e distribui encomendas na Alemanha. Se uma loja enviou a tua encomenda para uma morada alemã através da J&T, o número de seguimento começa por `JTDE`: cola-o na caixa acima ou pesquisa-o na [página de seguimento da J&T em jtexpress.de](https://www.jtexpress.de/tracking). Quem tem o código é a loja que expediu a compra — aparece no e-mail de envio ou na página da encomenda.

## Número de seguimento da J&T Express Germany: como é

Nas perguntas frequentes da J&T lê-se que os seus números de envio começam por JTDE. O Peek conhece apenas um formato para esta rede: `JTDE` seguido de 12 algarismos, 16 caracteres ao todo. Com algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `JTDE999999999980` | `JTDE` e 12 algarismos: uma encomenda da J&T Express Germany. O Peek indica logo a J&T Express Germany. |
| `JTDE99999999980` | Um algarismo a menos: não tem a forma da J&T Express Germany e o Peek não reconhece nenhuma transportadora. Confirma se não saltaste um dígito. |

Se o código tiver um algarismo a menos ou a mais depois de `JTDE` e nenhuma transportadora responder por ele, a página da encomenda em peektracker.com avisa-te («Começa como um número de seguimento de J&T Express Germany, mas falta-lhe um carácter: pode estar mal escrito. Compara-o com a tua mensagem de envio.» ou «… mas tem um carácter a mais: pode estar mal escrito. Compara-o com a tua mensagem de envio.»).

**Onde encontrá-lo.** A J&T indica que é o remetente quem to fornece e que o encontras na página da encomenda da loja online, no e-mail de confirmação da compra, no e-mail ou SMS de expedição, nos e-mails que anunciam ou confirmam a entrega e na etiqueta da caixa. Se não o encontrares, a J&T remete-te para o remetente ou para o apoio ao cliente da plataforma onde compraste. Vê também [onde encontrar o número de seguimento](guide:find-tracking-number).

A pesquisa da J&T aceita até 10 números de uma só vez, separados por espaços ou vírgulas.

**Códigos parecidos noutras redes.** As redes da J&T noutros países têm páginas e formatos próprios: `JTUK` e 12 algarismos pertence à J&T Express UK, `JTAU` e 12 algarismos à J&T Express Australia, `JTE` e 12 algarismos à J&T Express Middle East, enquanto `JT` com 13 algarismos é usado pela J&T no Sudeste Asiático e na China (J&T Express China); também operam em redes separadas mercados como a J&T Brasil, que iniciou operações oficialmente em 2022. Só `JTDE` pertence à rede alemã. Se a pesquisa da J&T responder `Keine Abfrageergebnisse` (`No tracking records yet` em inglês) ou o Peek continuar a mostrar «À espera de J&T Express Germany», compara o código com o e-mail da loja ou pede confirmação ao vendedor.

## Estados de seguimento da J&T Express Germany explicados

No seguimento da J&T cada leitura traz apenas data, hora e uma frase, sem localidade. O site abre em alemão e não tem versão portuguesa; se mudares o idioma para inglês ou chinês no menu da página, volta a carregar as leituras nessa língua. O Peek pede as frases em inglês e mostra-as sem tradução, pela ordem em que costumam surgir no percurso da encomenda:

| A J&T escreve | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | A J&T recolheu a encomenda junto do remetente. | «Aceite pela transportadora» |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | Chegou a um centro de distribuição da J&T. | «Em trânsito» |
| `The shipment is dispatched from the transit center to the next destination` | Saiu desse centro de triagem rumo à etapa seguinte. | «Em trânsito» |
| `Your package has been loaded and is on its way to you` | Já vai na carrinha: saiu para distribuição. | «Em distribuição» |
| `Delivery failed because the address could not be found` | O estafeta não conseguiu localizar a tua morada. | «Tentativa de entrega» |
| `Delivery failed, customer refused to accept` | O destinatário recusou receber a encomenda. | «Tentativa de entrega» |
| `Your parcel has been delivered to the pickup point. Please check your email for details` | O estafeta deixou a encomenda num ponto de recolha; a J&T envia os detalhes por e-mail. | «Pronta para levantamento» |
| `Your package has been successfully delivered.` | Entregue. A J&T acrescenta o modo de entrega (vê abaixo). | «Entregue» |
| `Returns have begun` | Após uma entrega falhada, a encomenda está a voltar para o remetente. | «Devolvida ao remetente» |
| `The shipment has been returned` | A devolução ao remetente ficou concluída. | «Devolvida ao remetente» |

Uma frase que não conste desta tabela segue as regras gerais que o Peek usa para todas as transportadoras; se o Peek também não a reconhecer assim, mostra-a tal como a J&T a escreveu e conta a encomenda como «Em trânsito», a menos que já esteja em distribuição ou numa fase seguinte, caso em que mantém o estado anterior.

A linha de carregamento (`Your package has been loaded and is on its way to you`) pode repetir-se várias vezes: a J&T escreve-a por cada volta de distribuição. A leitura de entrega prossegue com o tipo de entrega entre parênteses 【】 e fecha com uma nota de apoio ao cliente. O Peek retira essa nota final e guarda o tipo, sem parênteses, apenas quando é um destes seis:

- `Left with neighbor`: entregue a um vizinho;
- `Front Door Porch`: à porta de entrada;
- `Front Desk Reception`: na receção;
- `Garden Yard`: no jardim ou pátio;
- `Mailbox`: na caixa do correio;
- `Garage`: na garagem.

Assim, uma encomenda deixada a um vizinho surge no Peek como `Your package has been successfully delivered. The delivery type is Left with neighbor.`

**Que vizinho ficou com ela?** Na página da J&T, por baixo dessa leitura aparece `Zustelldetails anzeigen` (`Click to view delivery details` no site em inglês). Clica aí e introduz o código postal da morada de entrega para veres o nome do vizinho e o número da porta; ao fim de cinco códigos postais errados tens de aguardar antes de tentar de novo. O Peek consulta apenas com o número de seguimento e mostra só que foi deixada a um vizinho, sem nome nem morada. Também retira quaisquer nomes ou números de telefone que venham numa linha da J&T.

## Entrega da J&T na Alemanha: vizinhos, local seguro e fotografia

Nos históricos que o código do Peek conhece, o estafeta da J&T carrega a encomenda para uma volta de distribuição e depois entrega-a, deixa-a num ponto de recolha ou regista o motivo pelo qual não conseguiu entregar. O código do Peek não mostra qualquer passagem a outra transportadora: o seguimento da J&T acompanha a encomenda até à entrega. Quem toca à campainha pode trabalhar para um dos parceiros em franchising da J&T: no site alemão, a empresa procura parceiros em toda a Alemanha cujas equipas assegurem entregas e recolhas de última milha.

**Em distribuição.** `Your package has been loaded and is on its way to you` quer dizer que a encomenda saiu numa volta de entrega. Nesses históricos a leitura surge de manhã, algumas horas antes da entrega. O seguimento da J&T não indica janela horária nem dia previsto de entrega, pelo que o Peek também não os pode mostrar.

**Preferências de entrega.** O link na notificação de expedição leva à [página de preferências de entrega da J&T](https://www.jtexpress.de/delivery-preference) e contém o teu número de seguimento encriptado. Confirma o código postal e escolhe uma das opções propostas pela J&T: entrega com assinatura a ti próprio, a um vizinho (indicas o nome e se mora na porta ao lado ou no mesmo andar) ou numa receção, ou entrega sem assinatura à porta de entrada, no jardim, na caixa do correio ou na garagem. Segundo a J&T, o estafeta segue a tua escolha — evitando segundas entregas e entregas erradas — e os dados inseridos servem apenas para essa entrega. Se escolheres um local sem assinatura, a página avisa que o risco de perda passa a ser teu assim que a encomenda lá é deixada. Se o estado do envio já não permitir alterações, a página informa-o.

**Fotografia de entrega.** De acordo com o aviso de privacidade da J&T sobre o comprovativo fotográfico de entrega (POD), o estafeta fotografa a encomenda quando ta entrega ou a deixa no local combinado, enquadrando apenas o volume e a parte necessária do local; quem apareça por acaso na imagem é desfocado de forma automática e irreversível. Por regra, a J&T apaga a fotografia 6 meses após a entrega, mas pode guardá-la até ficar encerrada uma reclamação aberta dentro desse prazo e mostrá-la ao remetente para esclarecer o caso.

**Recusada ou morada não encontrada.** Depois de `Delivery failed, customer refused to accept`, nos históricos que o código do Peek conhece o estafeta voltou a carregar a encomenda na manhã seguinte para nova volta. Depois de `Delivery failed because the address could not be found`, revê a morada da tua encomenda. Se faltar algum detalhe, como o andar ou o teu apelido na campainha, avisa já a loja: foi ela que marcou o envio com a morada que lhe indicaste. As FAQ da J&T também dizem para contactar de imediato o remetente ou o apoio da plataforma de compras — depois de expedida a encomenda, a possibilidade de alterar a morada depende do ponto em que se encontra e das regras do remetente. `Returns have begun` pode surgir após uma tentativa falhada. Vê [como alterar a morada de entrega](guide:change-delivery-address) e [entrega falhada](guide:missed-delivery).

**Tentativas e pontos de levantamento.** O site da J&T não indica número de tentativas de entrega, prazo de guarda nem horário de distribuição, nem publica uma lista ou mapa próprio de lojas de encomendas, cacifos ou armazéns. Ainda assim, no seguimento da J&T Express Germany pode surgir a leitura `Your parcel has been delivered to the pickup point. Please check your email for details` quando, após sair para distribuição, o estafeta deixa a encomenda num ponto de recolha e a J&T envia por e-mail os dados para a levantares.

## Encomenda da J&T atrasada, desaparecida ou devolvida

Foi a loja que escolheu a J&T, por isso numa compra online a loja é sempre o teu primeiro contacto. Segundo a lei alemã (§ 475 do Código Civil alemão, BGB), quando compras a uma empresa enquanto consumidor, o vendedor suporta o risco de perda ou dano durante o transporte, salvo se tiveres contratado tu próprio uma transportadora que o vendedor não te tivesse indicado previamente.

**Sem atualizações.** A J&T não publica prazos de entrega para destinatários. Uma encomenda parada em «Aceite pela transportadora» ou «Em trânsito» durante quatro dias sem novas leituras recebe um aviso no Peek com o dia da última leitura («Sem atualizações desde seg 12 out») e o link «Pede ajuda a J&T Express Germany». Se continuar sem mexer, fala com a loja ou liga para a linha de apoio da J&T com o teu código. Mais informações em [seguimento que não atualiza](guide:tracking-not-updating).

**Ainda sem registo.** Um número que o seguimento da J&T ainda desconhece aparece como «À espera de J&T Express Germany». Muitas lojas geram o número antes de entregarem a caixa à transportadora. Se ao fim de dois dias a J&T continuar sem reconhecer o código e mais nenhuma fonte tiver respondido por ele, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.», passando o Peek a verificá-lo uma vez por dia e, passado uma semana desde que o adicionaste, uma vez por semana. Confirma o número com a loja e pergunta se a encomenda já seguiu mesmo.

**Dada como entregue, mas não recebida.** Lê a linha de entrega no Peek: se indicar um sítio como `Mailbox` ou `Garage`, procura aí; se disser `Left with neighbor`, pergunta aos vizinhos ou vê qual deles a recebeu na página da J&T introduzindo o código postal (vê acima). As FAQ da J&T pedem também para confirmar se familiares, vizinhos ou funcionários do prédio receberam a encomenda. Continua desaparecida? Participa o caso na linha telefónica da J&T e pede à loja o reembolso ou novo envio, indicando o número de seguimento. Consulta também [encomenda entregue mas não recebida](guide:delivered-not-received).

**Perdida, danificada ou devolvida.** Pede o reembolso ou a substituição à loja com o número de seguimento. As FAQ da J&T não trazem formulário de reclamação, prazos nem indemnizações para destinatários; os prazos de participação decorrem da lei alemã. Ao abrigo do § 438 do Código Comercial alemão (HGB), um dano visível deve ser participado o mais tardar no ato da entrega (basta dizê-lo a quem entrega), e um dano oculto nos sete dias seguintes em suporte de texto, como um e-mail para a J&T; o envio atempado basta para cumprir o prazo. Se não chegares a acordo com a J&T sobre uma encomenda perdida ou danificada, podes recorrer gratuitamente através de formulário online à comissão de arbitragem postal da Bundesnetzagentur (Schlichtungsstelle Post), aberta tanto a destinatários como a remetentes. As encomendas de grandes lojas online viajam porém, em regra, ao abrigo de condições especiais que excluem a arbitragem; nesse caso podes recorrer ao serviço de apoio ao consumidor postal da Bundesnetzagentur (Verbraucherservice Post). Vê [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [encomenda devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos da J&T Express Germany

Canais da J&T Express Germany verificados na sua [página de contactos](https://www.jtexpress.de/contact) em 16 de novembro de 2026:

- **Telefone:** +49 6931090650 (069 31090650 a partir da Alemanha), de segunda a sexta-feira das 8:00 às 20:00, encerrado nos feriados.
- **E-mail:** `de-jtservice@jtexpress.de`.
- **Chat em direto:** botão «Chat starten» («Start Chat» na versão inglesa), nas páginas de contactos e FAQ.
- **Fotografias de entrega e proteção de dados:** `de-dpo@jtexpress.de` (indicado no aviso de privacidade POD).
- **Escritório:** J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm. O aviso legal (Impressum) indica a sede social em Wiesenhüttenstraße 11, 60329 Frankfurt am Main.

A J&T não refere em que línguas atende o apoio ao cliente; o site existe em alemão, inglês e chinês. Para uma compra online, começa ainda assim pela loja: foi ela quem contratou o envio com a J&T. Num marketplace, usa a ajuda na página da encomenda. Numa encomenda parada, o link «Pede ajuda a J&T Express Germany» no Peek abre a página de contactos da J&T.

## Seguir uma encomenda da J&T Express Germany com o Peek

O [Peek](/) identifica logo a J&T Express Germany a partir de um código `JTDE` («Transportadora detetada»), escrito com ou sem espaços, em maiúsculas ou minúsculas, e consulta o próprio seguimento da J&T apenas com o número: sem código postal nem conta. Também funciona um link `www.jtexpress.de/tracking` com `?q=` e o código; se lá estiver um número de outro formato, o Peek lê-o como se o tivesses colado sozinho. Se o teu código não for um `JTDE`, cola-o na mesma: o Peek acompanha mais de 4.500 transportadoras. Escolher a J&T Express Germany em «Alterar» para outro formato não ajuda: o Peek avisa que ela «não emite números como este».

O Peek mostra o estado, cada leitura com a hora exata e o momento da entrega. Como a J&T regista cada leitura num instante exato, o Peek apresenta-a no fuso horário do teu dispositivo; tanto no peektracker.com como nas apps para iPhone e Android uma linha no fim do histórico lembra-o («Horas no teu fuso horário: …»). As leituras vêm no inglês da J&T, mesmo que em jtexpress.de as leias em alemão. A J&T não dá localidade, dia previsto de entrega nem peso, por isso o Peek não mostra mapa. Num histórico muito longo guarda as 100 leituras mais recentes. «Abrir o site de J&T Express Germany», na página da encomenda, abre o teu código no seguimento da J&T.

Enquanto a J&T não ler a encomenda, o Peek continua a perguntar e mostra «À espera de J&T Express Germany» (ou, se dois dias depois a J&T ainda não conhecer o número, o pedido para o verificares). Quando o seguimento da J&T não está acessível ou desconhece o código, o Peek consulta serviços de seguimento e apresenta o histórico deles, nas palavras deles. Se mais tarde tanto a J&T como os serviços de seguimento responderem sem qualquer leitura para uma encomenda que já tinha registos, o Peek guarda os já encontrados e, se isso durar, escreve: «A transportadora não tem registos novos por agora. Os já encontrados ficam guardados, e o Peek continua a verificar.»

O Peek consulta o seguimento até a cada 10 minutos, e até a cada 2 minutos quando a encomenda sai para entrega: só nesse dia, a partir das 6h, hora local, e enquanto responder o próprio seguimento da J&T (através de serviços de seguimento, a cada 15 minutos). Depois de dois dias sem novidades, ou de 12 horas se a encomenda ainda estiver em «Aceite pela transportadora», o Peek passa a consultar apenas uma vez por hora. Pode enviar-te notificações: «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a J&T nem a loja por ti, alterar a morada ou marcar nova entrega.

## Perguntas frequentes sobre o seguimento J&T Express Germany

### A J&T Express Germany é fidedigna?

Sim. A J&T Express Germany GmbH, registada em Frankfurt am Main, é a empresa alemã da J&T Express, grupo de entregas expresso fundado na Indonésia em 2015 e cotado na Bolsa de Hong Kong. O regulador postal alemão, a Bundesnetzagentur, inscreveu a sociedade no seu registo de prestadores postais (Anbieterverzeichnis Post) em 1 de setembro de 2026. A J&T procura parceiros em franchising por toda a Alemanha para entregas e recolhas de última milha, e todas as frases da tabela de estados acima foram lidas em encomendas reais da J&T Express Germany.

### Que vizinho tem a minha encomenda da J&T?

O seguimento da J&T diz-to: por baixo da leitura de entrega, clica em `Zustelldetails anzeigen` (`Click to view delivery details` em inglês) e introduz o código postal da morada de entrega para veres o nome do vizinho e o número de porta. O Peek mostra apenas `Left with neighbor`, sem nome nem morada.

### A J&T Express tem pontos de recolha ou cacifos na Alemanha?

O site alemão da J&T não lista nenhum, mas uma linha da J&T Express Germany conhecida pelo Peek indica que o estafeta deixou a encomenda num ponto de recolha e que a J&T envia os detalhes por e-mail. Na página de preferências de entrega podes pedir a entrega em mão, a um vizinho, numa receção ou num local seguro na tua morada.

### Porque é que o meu código de seguimento J&T não é encontrado?

Confirma se começa por `JTDE` seguido de 12 algarismos: códigos de outras redes da J&T, como `JTUK` no Reino Unido, não pertencem à rede alemã. Se o código estiver certo, a J&T pode ainda não ter recebido a caixa: o seu site responde `Keine Abfrageergebnisse` (`No tracking records yet`) e o Peek mostra «À espera de J&T Express Germany». Se dois dias depois nem a J&T nem outra fonte conhecerem o código, o Peek pede-te para o verificares, escolheres outra transportadora ou eliminares a encomenda. Se passados vários dias continuar sem registo, fala com a loja.

### Como contacto a J&T Express na Alemanha?

Liga para o +49 6931090650 (de segunda a sexta-feira, das 8:00 às 20:00), usa o chat em direto em jtexpress.de ou escreve para `de-jtservice@jtexpress.de`. Numa compra online, pergunta primeiro à loja que expediu a encomenda.

:::sources
- [J&T Express Germany: Track & Trace](https://www.jtexpress.de/tracking) – seguimento, até 10 números, línguas do site e recarregamento das leituras, mensagem sem resultados, dados do vizinho após validação do código postal e cinco tentativas
- [J&T Express Germany: Frequently Asked Questions](https://www.jtexpress.de/faq) – códigos JTDE e onde encontrá-los, entregue mas não recebida, morada errada, chat em direto; sem prazos de entrega, tentativas nem regras de reclamação
- [J&T Express Germany: Delivery Preferences](https://www.jtexpress.de/delivery-preference) – link no e-mail de aviso, verificação do código postal, opções com e sem assinatura, risco de perda no local escolhido, menos segundas entregas e entregas erradas, dados usados só nessa entrega
- [J&T Express Germany: Contact Us](https://www.jtexpress.de/contact) – telefone, horário, chat em direto, e-mail e morada do escritório em Nieder-Olm
- [J&T Express Germany: Legal notice](https://www.jtexpress.de/legal-statement) – J&T Express Germany GmbH, Frankfurt am Main
- [J&T Express Germany: POD Privacy Policy](https://www.jtexpress.de/pod-policy) – fotografias de entrega, desfocagem automática, eliminação após 6 meses, reclamações e contacto de proteção de dados
- [J&T Express Germany: Become Our Partner](https://www.jtexpress.de/career) – parceiros em franchising em toda a Alemanha para entregas e recolhas de última milha
- [J&T Express Germany: About Us](https://www.jtexpress.de/about) – fundação em 2015 na Indonésia, cotação em Hong Kong e arranque da J&T Brasil em 2022
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – nomes, formato JTDE, links de seguimento, frases lidas em encomendas reais e respetivas fases, voltas de distribuição, tipos de entrega, elementos retirados e página de ajuda
- [Universal Parcel Scraper: consulta comum das redes J&T](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – frases em inglês, horas exatas das leituras, sem localidade, dia previsto nem peso, remoção de nomes e números de telefone, 100 leituras mais recentes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos das outras redes J&T, ausência de passagem a outra transportadora e serviços de seguimento como alternativa
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – risco do transporte suportado pelo vendedor nas vendas de empresas a consumidores
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – participação de dano visível na entrega, dano oculto em sete dias em suporte de texto e cumprimento do prazo pelo envio atempado
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – registo de prestadores postais: inscrição da J&T Express Germany GmbH em 1 de setembro de 2026
- [Bundesnetzagentur: Häufig gestellte Fragen zur Schlichtung Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – pedido online gratuito aberto também a destinatários, condições especiais nas encomendas de grandes lojas online e Verbraucherservice Post
:::

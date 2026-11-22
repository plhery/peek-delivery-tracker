---
title: Seguimento Whistl: quem entrega a encomenda e o que fazer
description: Acompanha uma encomenda Whistl (Parcelhub): onde está a referência, significado dos estados, quem faz a entrega final e a quem pedir ajuda.
slug: seguimento-whistl
published: 2026-11-22
updated: 2026-11-22
---

A Whistl, antiga TNT Post UK, é uma empresa britânica de entregas que expede encomendas para lojas online e as entrega a transportadoras como [Royal Mail](carrier:royal-mail), [Evri](carrier:evri) ou [DPD UK](carrier:dpd-uk) para a entrega final. Acompanha-a no rastreador Parcelhub da Whistl com a referência do e-mail de expedição da loja, ou cola a ligação na caixa acima. Após a passagem, o número e o rastreador da transportadora final costumam dar mais detalhe.

## Número de seguimento Whistl: onde o encontrar

A Whistl não publica um formato fixo. Os seus guias indicam que o rastreador pesquisa «por UPI (normalmente o código de barras do volume) ou Consignment Number»: o UPI identifica uma encomenda e o número de remessa agrupa tudo o que segue junto. As referências variam conforme a loja e a transportadora, desde uma letra seguida de muitos dígitos (como `L48291736482917364`) até ao código de barras da própria transportadora final.

Encontra-se no e-mail de envio da loja, muitas vezes como ligação para o rastreador da Whistl. Nas encomendas com seguimento completo (*fully tracked*), a transportadora final envia também e-mail e SMS no dia da entrega, sob a sua marca e com o seu número.

O rastreador pede «Tracking Reference» e um código postal opcional, mostrando depois para cada volume o «Tracking Number:», os registos e os campos «Carrier:» e «Service:».

Uma referência pode ter o aspeto do código de outra transportadora, como o da transportadora final (caracteres inventados):

| Formato (inventado) | A quem se assemelha |
| --- | --- |
| `L48291736482917364` | `L` e 17 dígitos: referência Whistl que o Peek consulta diretamente |
| `RR482917367GB` | Royal Mail (duas letras, nove dígitos, `GB`) |
| `H00KLM0048291708` | Código Evri de 16 caracteres, vê [seguimento Evri](carrier:evri) |
| `48291736482917` | 14 dígitos: DPD UK entre várias outras, vê [DPD UK](carrier:dpd-uk) |
| `ZDBKLM482917` | XDP |

Mais ajuda sobre códigos em [formatos de números de seguimento](guide:tracking-number-formats).

## Significado dos estados de seguimento Whistl

O rastreador da Whistl, apenas em inglês, converte os registos de cada transportadora nos mesmos «eventos normalizados»; encomendas totalmente seguidas mostram também comentários da transportadora. Por ordem habitual:

| A Whistl indica | O que significa para ti |
| --- | --- |
| `Shipment Created` | A loja registou o envio. Ainda não se moveu. |
| `Item receipted and processed by Whistl` | A Whistl recebeu e processou a encomenda num dos seus depósitos. |
| `Handed over to Delivery Carrier` | A transportadora indicada na página assegura agora a entrega. |
| `Handed over to Royal Mail` | A Royal Mail assumiu a entrega final. |
| `Arrived At Depot` | Chegou a um depósito intermédio. |
| `Shipment is Out for Delivery` | Em distribuição hoje. |
| `DELIVERED - Left as authorised` | Deixada no local autorizado, sem assinatura. |
| `Left with Neighbour` | Entregue a um vizinho. |
| `Delivered col. point` | A aguardar levantamento num ponto de recolha. |
| `No-one to receive` | Não estava ninguém: procura um aviso ou mensagem da transportadora. |
| `Held by customs` | Retida na [alfândega](guide:customs). |
| `Return - Delivered` | Devolvida e entregue novamente ao remetente. |

Três pontos causam frequentemente dúvidas:

- **`Shipment Created` isolado** indica apenas reserva. Se nada surgir durante dias, a loja pode ainda não ter entregue a encomenda.
- **`Handed over to …`** é muitas vezes o último registo mostrado pela Whistl. Em envios com seguimento parcial (*part-tracked*), a Whistl refere que não é possível seguir artigos após a passagem: acompanha antes com o número da transportadora final.
- **Problemas** como `Delayed`, `Bad Address`, `Refused`, `Lost` ou `Item damaged` sinalizam uma ocorrência; `Shipment Deleted` significa cancelamento pelo remetente. Linhas como `Item Weight Changed` ou `Label reprinted` são notas internas sem deslocação da encomenda.

## Entregas Whistl e entregas falhadas

Quem entrega a encomenda é a transportadora final, não a Whistl, aplicando-se as regras dela. Após uma tentativa falhada, essa transportadora deixa um aviso em papel ou digital indicando quem passou e quando voltará.

O guia de transportadoras Parcelhub da Whistl (agosto de 2025) resume por operador:

| Transportadora | Tentativas e passo seguinte |
| --- | --- |
| Royal Mail | 1 tentativa e cartão «something for you»; levantamento em posto de entrega ou estação postal, ou devolução após 18 dias. Desde maio de 2023, se não couber na caixa ou precisar de assinatura na tua ausência, fica em local seguro ou num vizinho. |
| Evri | Até três tentativas; preferências na app Evri; janela de 2 horas por e-mail ou SMS. |
| DPD | Aviso mais SMS ou e-mail para escolher vizinho, local seguro, nova data ou ponto de recolha; janela de uma hora. |
| Yodel | Até três tentativas; moradas insulares podem ter apenas uma antes de levantamento numa estação postal. |
| [Amazon Shipping](carrier:amazon-shipping) | Até três tentativas em 3 dias consecutivos. |
| DHL Parcel UK | Até 2 tentativas. |
| Parcelforce | 1 tentativa, com opções de local seguro e redirecionamento. |
| [UPS](carrier:ups) | 1 tentativa, seguida de notificação digital com instruções de nova entrega. |

Envios internacionais parcialmente seguidos passam a parceiros estrangeiros, seguindo as regras dos correios locais. A Whistl não tem pontos de levantamento próprios: `Delivered col. point` designa a loja ou cacifo da transportadora final. Uma encomenda recusada volta ao remetente sem nova tentativa.

Marcada como entregue mas não aparece? Os guias da Whistl recomendam verificar primeiro o aviso, locais seguros e vizinhos. Depois vê [entregue mas não recebida](guide:delivered-not-received).

## Encomenda Whistl não entregue, atrasada ou perdida

A página de contacto da Whistl indica ao público que «contacte o retalhista onde efetuou a compra». A loja detém o contrato, abre uma averiguação «Where Is My Item» junto da Whistl e apresenta qualquer reclamação.

Segundo os guias da Whistl:

- Uma encomenda no Reino Unido que não possa ser entregue volta ao remetente no prazo de 5 dias úteis após entrar na rede Whistl.
- Em envios com seguimento parcial não há compensação por atraso, e a Whistl não pode intervir após a entrega ao parceiro sem entrega final.
- Os prazos de reclamação do remetente variam por transportadora: por exemplo 28 dias após expedição na Evri, ou na Royal Mail espera mínima de 10 dias de calendário antes de reclamar e prazo até 80 dias. Contacta a loja cedo.

Se o seguimento parar, começa por [porque é que o seguimento para de atualizar](guide:tracking-not-updating).

## Telefone e apoio ao cliente da Whistl

Contactos na página da Whistl:

- **Sobre a tua encomenda:** a loja onde compraste.
- **Seguimento:** o botão «Whistl Tracking Portal» na página de contacto.
- **Informações gerais:** `01628 702 965`, das 08:30 às 17:30 hora do Reino Unido. Esta linha serve clientes empresariais da Whistl.
- **E-mail Customer Services:** `packagescustomerservices@whistl.co.uk`, que «não pode ajudar o público em questões de seguimento de envios».
- **Sede:** Network House, Third Avenue, Marlow, Buckinghamshire, SL7 1EY.

Na Grécia, Chipre, Luxemburgo e Malta a Whistl não tem escritório nem linha telefónica. Contacta a loja e, após a passagem, a transportadora ou correios locais indicados no rastreador.

## Seguir uma encomenda Whistl com o Peek

O [Peek](/) acompanha mais de 4500 transportadoras. Numa referência de 18 caracteres começada por `L` seguida de 17 dígitos, o Peek pergunta diretamente à Whistl e mostra «Whistl tem esta encomenda.» assim que a Whistl a reconhece. Outras referências Whistl não têm formato próprio: cola uma ligação Whistl (`web.parcelhub.net` ou `despatch.whistl.co.uk` com `/Tracking/reference/…`, ou `trackmyitem.whistl.co.uk` com `/tracking/…`), que identifica logo a Whistl («Transportadora detetada»), ou adiciona a referência e escolhe «Whistl» em «Alterar». Não precisas de código postal. Uma referência com o formato de outra transportadora (como um código Royal Mail acabado em `…GB`) acompanha essa outra transportadora, não a Whistl.

O Peek lê o rastreador Parcelhub da Whistl. As horas não trazem fuso: o Peek lê-as na hora local do registo (hora britânica no Reino Unido) e mostra-as no teu fuso («Horas no teu fuso horário:» seguido do nome). Apresenta estado, serviço, o nome de quem assinou na entrega, o número canónico Whistl se diferir da referência e a transportadora final com o respetivo número. Não há dia previsto de entrega, peso nem ponto de levantamento. Os estados mantêm o inglês da Whistl, exceto `Delivered`, `In transit` e `Returned to sender`, traduzidos pelo Peek.

Quando a Whistl indica Royal Mail, Parcelforce Worldwide, DPD UK, DHL Parcel UK (DHL eCommerce UK), Amazon Shipping, [InPost](carrier:inpost), UPS ou [FedEx](carrier:fedex), o Peek pode continuar o seguimento lá. Se indicar apenas Evri, DPD ou Yodel não continua automaticamente: adiciona o número próprio dessa transportadora, ou usa o site da Yodel, pois o Peek não acompanha a Yodel. Uma referência que cubra vários volumes não é seguida: adiciona cada número individual.

Enquanto tiveres a encomenda aberta no Peek — ou no dia da entrega com notificações ativas — o Peek consulta diretamente a Whistl até cada 10 minutos, e até cada 2 minutos (ou cada 5 minutos quando precisa de abrir o rastreador da Whistl num navegador) a partir das 6:00 na hora do último registo (hora do Reino Unido no Reino Unido) no dia em que a Whistl assinala «Em distribuição», enquanto o rastreador responder; fora disso, e após dois dias sem novidades (12 horas enquanto estiver em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Para notificações, escolhe «Todos os registos», «Passos importantes» ou «Só a entrega». Após quatro dias sem registos em trânsito, fora de alfândega ou problema reportado, o Peek mostra a data do último registo («Sem atualizações desde …») e refere «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Whistl.», com a ligação «Pede ajuda a Whistl» para a página de contacto. O Peek não pode contactar a Whistl nem a loja por ti.

## Perguntas frequentes sobre a Whistl

### A Whistl faz parte da Royal Mail?

Não. A Whistl UK Ltd é uma empresa separada, antiga TNT Post UK. Entrega muitos volumes à Royal Mail para o último troço, razão pela qual aparece `Handed over to Royal Mail`.

### Quem entrega encomendas da Whistl?

Normalmente outra transportadora: DPD, Evri, Yodel, Royal Mail, Amazon Shipping, DHL Parcel UK, Parcelforce ou UPS no Reino Unido, e parceiros locais no estrangeiro.

### O seguimento Whistl é fidedigno?

Sim. O seguimento oficial funciona em `web.parcelhub.net`, `despatch.whistl.co.uk` e `trackmyitem.whistl.co.uk`, e a Parcelhub integra o Grupo Whistl.

### Porque é que o meu número Whistl não funciona?

O rastreador responde «Unable to find shipment» quando desconhece a referência. Confirma-a com a loja ou segue o número da transportadora final recebido por SMS ou e-mail.

:::sources
- [Whistl: Contact Us](https://www.whistl.co.uk/contact) – público encaminhado para o retalhista, telefone e horário, e-mail Customer Services, portal de seguimento, sede
- [Whistl: home page](https://www.whistl.co.uk/) – Whistl UK Ltd, serviços empresariais, Parcelhub parte do Grupo Whistl
- [Whistl: Domestic Fully Tracked Customer Guide (October 2025)](https://www.whistl.co.uk/1817/6062/1711/Whistl_Domestic_Fully_Tracked_Customer_Guide_-_V2.2_October_2025.pdf) – UPI e remessa, eventos normalizados, SMS e e-mails das transportadoras, aviso de ausência, recusa, perda, 5 dias úteis, reclamações do remetente
- [Whistl: Part Tracked Services Customer Guide v1.7](https://www.whistl.co.uk/download_file/force/3171/250) – passagem à Royal Mail e parceiros, 18 dias, sem compensação por atraso, ligações de seguimento, e-mails de marca
- [Parcelhub: Domestic Carrier Guide (August 2025)](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Carrier_Guide_Domestic_-_V1.1_Aug_2025.pdf) – transportadoras usadas, tentativas e opções, prazos de reclamação
- [Parcelhub: Part Tracked Services Customer Guide V1.0](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Part_Tracked_Customer_Guide_-_V1_September_2024.pdf) – portais de seguimento, pesquisa por UPI ou remessa, sem apoio após passagem
- [Parcelhub: Tracking](https://web.parcelhub.net/Tracking) – campos do formulário, aspeto dos resultados, apenas inglês
- [Whistl Despatch: Tracking](https://despatch.whistl.co.uk/tracking) – mesmo rastreador sob nome Whistl
- [Wikipedia: Whistl](https://en.wikipedia.org/wiki/Whistl) – mudança de nome de TNT Post UK, último troço Royal Mail
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – campos lidos pelo Peek, estados registados, deteção e frequência de verificação
:::

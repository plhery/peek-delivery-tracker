---
title: Rastreio Chukou1: códigos EC-FirstClass, entrega e ajuda
description: Rastreia uma encomenda internacional Chukou1 (CK1 / 出口易) via EC-FirstClass: estrutura do código, estados, entrega local e contactos na China.
slug: rastreio-chukou1
published: 2026-11-24
updated: 2026-11-24
---

A Chukou1 (*CK1*, em chinês *出口易*, operada pela *Guangzhou Beifayi Information Technology Co., Ltd.* / *BFE*) é um grande operador chinês de logística de comércio eletrónico transfronteiriço e armazenagem no estrangeiro, sediado em Guangzhou, com delegações em Shenzhen, Xangai e Wuhan e centros de processamento em Dongguan e Yiwu. Operando armazéns de logística nos Estados Unidos, Reino Unido, Alemanha, França, Polónia, Canadá, Austrália, Peru e Argentina, bem como linhas dedicadas de pequenas encomendas (super linhas Europa/EUA, linhas económicas globais, América Latina e TEMU-Y2) para vendedores na Amazon, eBay, AliExpress, SHEIN, TikTok Shop, Walmart e Wayfair, a Chukou1 rastreia envios através da sua plataforma **EC-FirstClass** ([ec.chukou1.com](https://ec.chukou1.com/), até 100 números de uma vez). Podes consultar um envio lá ou colar o teu código na caixa acima.

## Números de rastreio Chukou1 (formato EC-FirstClass)

Procura o número de processamento da Chukou1 na página do pedido do marketplace ou no e-mail de envio. Os códigos próprios da Chukou1 juntam um prefixo de serviço de 3 letras, uma data `AAMMDD` de 6 dígitos, um código de comerciante de 3 ou 4 caracteres e uma série de 6 ou 7 dígitos (18 a 20 caracteres no total):

| Formato (inventado) | O que é |
| --- | --- |
| `CZN261015ABCD482917` | Número de processamento Chukou1 de 19 caracteres (`CZN` + data `261015` + código cliente 4 car. `ABCD` + série 6 dígitos). |
| `CUE261015KLM4829173` | Número de processamento Chukou1 de 19 caracteres (`CUE` + data `261015` + código cliente 3 car. `KLM` + série 7 dígitos). |

Após o desalfandegamento de exportação, a Chukou1 entrega a encomenda a uma transportadora para o resto do percurso (a EC-FirstClass refere China Post, [USPS](carrier:usps), [DHL](carrier:dhl), [FedEx](carrier:fedex) e [UPS](carrier:ups) entre as parceiras) e mostra sob o teu número de processamento tanto o histórico inicial da Chukou1 como os registos reencaminhados dessa transportadora. Consulta [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da Chukou1 (EC-FirstClass)

A Chukou1 associa um estado global (`InProcess`, `InTransit`, `Delivered`) a etapas iniciais em inglês e a registos reencaminhados da transportadora de destino, usando a hora local de cada ponto de registo:

| A Chukou1 indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Shipment information received`, `Carrier received the manifest` | Pedido eletrónico recebido do vendedor antes da entrada física no centro da Chukou1. | «Etiqueta criada» |
| `Item received by Logistics Centre`, `Carrier received the shipment` | Encomenda física registada no armazém de origem da Chukou1 na China. | «Aceite pela transportadora» |
| `Item was sent out from Logistics Centre`, `Transfer to Airport of Origin`, `Departed from Airport of Origin`, `Arrived at Airport of destination`, `Shipment started the customs clearance process at origin` | Transferida do centro para o aeroporto de origem, exportada e chegada ao país de destino. | «Em trânsito» |
| `Customs clearance in progress`, `Shipment started the customs clearance process at destination`, `Shipment is awaiting customs clearance at destination` | Em inspeção aduaneira de importação no país de destino. | «Na alfândega» |
| `Customs declaration completed`, `Turned over to last mile facility`, `Last mile Ascan`, `Arrived at Post Office` | Desalfandegada na importação, entregue à transportadora local de última milha e registada na sua rede. | «Em trânsito» |
| `Shipment is out for delivery` / `Out for Delivery` | Carregada para distribuição final pela transportadora de destino. | «Em distribuição» |
| `Shipment is available for pickup` | Pronta para levantamento num posto dos correios ou ponto de recolha local. | «Pronta para levantamento» |
| `Wrong Address`, `Refused`, `Cannot be delivered` | Tentativa de entrega falhada por morada errada, recusa ou impossibilidade de entrega. | «Tentativa de entrega» |
| `Return To Sender`, `Delivered To Sender` | Em devolução ou devolvida ao remetente. | «Devolvida ao remetente» |
| `Lost` | Incidente assinalado como extraviada em trânsito; contacta de imediato o teu vendedor. | «Problema reportado» |
| `Shipment delivered to customer` / `Delivered` | Entregue pela parceira de destino (com nome de quem assinou quando registado). | «Entregue» |

## Entrega internacional, entregas locais e alfândega

Em [chukou1.com](https://www.chukou1.com/) e [ec.chukou1.com](https://ec.chukou1.com/), a Chukou1 opera como consolidador transfronteiriço B2B e operador de armazéns no estrangeiro, não como rede própria de entrega ao consumidor final.

- **A parceira local controla a entrega final:** assim que surgem `Turned over to last mile facility` e `Last mile Ascan`, tentativas de entrega, redirecionamentos, locais seguros e prazos de levantamento seguem o operador postal ou estafeta que assume a encomenda no teu país.
- **Alfândega e encargos de importação:** envios transfronteiriços para a UE, EUA, Reino Unido, Canadá ou Austrália passam por desalfandegamento no destino (`Customs clearance in progress`), IVA de importação e direitos aplicáveis (incluindo o direito aduaneiro da UE de 3 € até 150 € e a taxa de tratamento da UE de 2 € em pedidos de fora da UE), salvo pagamento pelo comerciante na compra. Vê [encomendas retidas na alfândega](guide:customs).

## Encomenda Chukou1 parada, sem atualizações ou desaparecida

Como são os comerciantes online que detêm a conta de envio junto da Chukou1, contacta sempre primeiro o vendedor ou marketplace para reembolsos, reenvios ou alterações de morada.

- **Pausas entre a China e o destino:** o transporte aéreo internacional e lotes aduaneiros costumam criar pausas de vários dias entre `Departed from Airport of Origin`, `Arrived at Airport of destination` e `Last mile Ascan`. Vê [por que razão o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Rastreia com o código local após a passagem:** quando a EC-FirstClass liga o código da parceira local, consultá-lo diretamente revela frequentemente janelas de entrega ou opções de nova tentativa.
- **Entregue, danificada ou perdida:** confirma primeiro junto de familiares, vizinhos e transportadora local e abre disputa com o vendedor antes de expirar a proteção de compra. Vê [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos Chukou1

Contactos oficiais publicados em [Contactos Chukou1](https://www.chukou1.com/About/Contact.aspx) e [chukou1.com](https://www.chukou1.com/):

- **Linha nacional na China:** `4006 908 223`.
- **Linha de reclamações (seg.–sex.):** `13360562736`.
- **Escritórios:** sede em Guangzhou `020-23380666` (Units 2403–04, Tower A1, Hangxin Centre, No. 840 Huangpu Avenue East, Huangpu District, Guangzhou), Shenzhen `0755-28263408`, Xangai `021-59793099`, Wuhan `027-59083347`.

> Desconfia de SMS falsas a pedir um pequeno pagamento online para «libertar» uma encomenda internacional na alfândega. Confirma sempre qualquer taxa legítima na página do pedido ou no site oficial dos teus correios. Vê [SMS fraudulentas de entregas](guide:delivery-scam-texts).

## Rastrear uma encomenda Chukou1 com o Peek

[O Peek](/) consulta automaticamente a Chukou1 como única candidata quando introduzes um número de processamento Chukou1 (`CZN261015ABCD482917` ou `CUE261015KLM4829173`, inventados) e mostra «Chukou1 tem esta encomenda.» quando a EC-FirstClass a confirma; o Peek reconhece também links colados de `ec.chukou1.com/track?track_number=` e `chukou1.com/LogistictsTrack.aspx?trackNo=` para códigos Chukou1. Se precisares de selecionar a Chukou1 manualmente, toca em «Alterar» ao adicionar ou em «Alterar transportadora» no ecrã da encomenda (pesquisar «出口易» ou «CK1» também a encontra).

O Peek lê diretamente o rastreio EC-FirstClass da Chukou1 e mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Na alfândega», «Em distribuição», «Pronta para levantamento», «Tentativa de entrega», «Problema reportado», «Devolvida ao remetente», «Entregue»), os registos da Chukou1 e os reencaminhados da transportadora de destino sem números de telefone nem nome do estafeta, a localidade e o país de destino. O código postal de destino e o nome de quem assinou mantêm-se privados e nenhuma app do Peek os mostra. A EC-FirstClass apresenta horas sem fuso: o Peek data cada registo no fuso horário do respetivo local e mostra-o na hora do teu dispositivo. Quando a EC-FirstClass indica a transportadora de entrega e o seu número e o Peek a reconhece, verifica se ela tem a encomenda: a página mostra então «Entrega:» seguido do nome da transportadora e lista ambos os números. O Peek omite o PDF de prova de entrega; a EC-FirstClass não fornece data estimada de entrega, peso, nome do serviço nem hora de entrega própria.

Enquanto tiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Chukou1 mostra um registo em distribuição, de 2 em 2 minutos a partir das 6h na hora local do último registo, enquanto a EC-FirstClass responder. Caso contrário, verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, passa a verificar de hora a hora seja qual for o modo de acompanhamento, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando a EC-FirstClass não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda mostra então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Chukou1.», com o link «Pede ajuda a Chukou1» para a página de contactos da Chukou1. Após três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Chukou1.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas as atualizações de rastreio e não pode desalfandegar encomendas, abrir PDFs de prova de entrega incorporados nem contactar a Chukou1 ou a tua transportadora local por ti.

## Perguntas frequentes sobre o rastreio Chukou1

### O que é a Chukou1 (CK1 / EC-FirstClass)?

A Chukou1 (*出口易*, operada pela *Guangzhou Beifayi Information Technology Co., Ltd.*) é um operador chinês de logística de comércio eletrónico transfronteiriço e armazéns no estrangeiro cujo portal de rastreio se chama **EC-FirstClass** ([ec.chukou1.com](https://ec.chukou1.com/)).

### Como é um número de rastreio da Chukou1?

Os números de processamento da Chukou1 têm 3 letras de prefixo, uma data `AAMMDD` de 6 dígitos, um código de comerciante de 3 ou 4 caracteres e uma série de 6 ou 7 dígitos (por exemplo `CZN261015ABCD482917`, inventado).

### O que significa `Last mile Ascan` no rastreio Chukou1?

`Last mile Ascan` significa que os correios ou estafeta local do teu país fizeram o primeiro registo de aceitação após receberem a encomenda da ligação internacional da Chukou1.

### Como contacto o apoio ao cliente da Chukou1?

Contacta primeiro a tua loja online para reembolsos ou alterações de morada. A linha chinesa publicada pela Chukou1 é `4006 908 223` (reclamações seg.–sex. `13360562736`, sede Guangzhou `020-23380666`) em [chukou1.com/About/Contact.aspx](https://www.chukou1.com/About/Contact.aspx).

:::sources
- [Chukou1: site oficial](https://www.chukou1.com/) – linhas de pequenas encomendas transfronteiriças, armazéns no estrangeiro (US, UK, DE, FR, PL, CA, AU, PE, AR), primeira milha FBA e sede em Guangzhou (*广州市贝法易信息科技有限公司*)
- [Chukou1: contactos](https://www.chukou1.com/About/Contact.aspx) – `4006 908 223`, reclamações `13360562736`, telefones e moradas em Guangzhou/Shenzhen/Xangai/Wuhan (URL de ajuda ligado pelo Peek)
- [EC-FirstClass (plataforma de rastreio Chukou1)](https://ec.chukou1.com/) – rastreio global de 100 números e transportadoras referidas (China Post, USPS, DHL, FedEx, UPS)
- [Universal Parcel Scraper: chukou1](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chukou1) – formato de número de processamento, regras de links `ec.chukou1.com`/`chukou1.com`, leitura de registos iniciais e parceiras, extração signatário/código postal/entrega, PDF POD omitido e URL de ajuda
:::

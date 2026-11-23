---
title: Seguimento Raben: número de envio, estados e entrega
description: Segue uma palete ou envio de grupagem Raben: formato do número, significado dos estados no myRaben, descarga, reclamações e contactos.
slug: seguimento-raben
published: 2026-11-22
updated: 2026-11-22
---

O Raben Group é um grupo logístico familiar europeu fundado em 1931 nos Países Baixos e atualmente sediado perto de Poznań, na Polónia. A sua rede rodoviária transporta paletes e grupagem (cargas parciais) através de filiais em 17 países europeus e também entrega para lojas online, pelo que uma encomenda volumosa pode chegar num camião Raben. Segue-a com o número de envio na caixa «Shipment tracking» em raben-group.com, ou cola o número acima.

## Número de envio Raben: como é e onde encontrar

A Raben atribui a cada envio o seu próprio número. Quem o tem é a empresa que reservou o transporte, geralmente a loja ou o fabricante: procura no e-mail de envio ou na página da encomenda. As empresas polacas da Raben indicam que recebem os dados do destinatário de quem encomendou o transporte, por isso pergunta ao remetente se não tiveres número. Mais em [como encontrar o teu número de seguimento](guide:find-tracking-number).

| Número (inventado) | O que é |
| --- | --- |
| `274829173648291` | 27 e mais 13 dígitos: o formato de um número de envio Raben no catálogo do Peek. |
| `27 4829 1736 4829 1` | O mesmo com espaços. Espaços e hífenes não importam no Peek. |
| `oftc.myraben.com/link/ShipmentInformation?ShipmentNumber=274829173648291` | Link direto para um envio no seguimento Raben, tal como o abre a caixa em raben-group.com. Cola o link inteiro no Peek. |

A [BRT](carrier:brt) em Itália e a Yunda na China também usam números de 15 dígitos, pelo que este formato nem sempre pertence à Raben. A pesquisa da Raben também encontra envios por referência ou etiqueta de volume, mas o Peek segue apenas o número de envio em si.

## O que significam os estados de seguimento da Raben

O seguimento da Raben, myRaben, apresenta o estado, cada passo com local e hora, data prevista de entrega, peso, número de volumes e volume total, barra de progresso, observações e contactos da filial Raben responsável. Os passos pela ordem habitual:

| A Raben indica | O que significa para ti | Estado no Peek |
| --- | --- | --- |
| `registered in the system` | O remetente reservou o transporte. A Raben tem o pedido, ainda não a mercadoria. | «Etiqueta criada» |
| `arrived to the pick-up place` | O camião chegou ao remetente para recolher o envio. | «Etiqueta criada» |
| `collected` | A Raben recolheu o envio no remetente. | «Aceite pela transportadora» |
| `loaded` | Carregado num camião, rumo ao depósito seguinte ou para distribuição. | «Em trânsito» |
| `unloaded` | Descarregado no depósito seguinte. | «Em trânsito» |
| `Inventory in a warehouse` | Registado no armazém de um depósito. | «Em trânsito» |
| `shipment in depo` | Num depósito. | «Em trânsito» |
| `in progress - advice required` | A caminho. A entrega tem de ser previamente avisada ao destinatário. | «Em trânsito» |
| `in progress - fix delivery` | A caminho, para entrega num dia fixo. | «Em trânsito» |
| `in delivery` | Em rota de entrega. | «Em distribuição» |
| `arrived to the delivery place` | O camião chegou à morada de entrega. | «Em distribuição» |
| `delivered` | Entregue. | «Entregue» |
| `delivered D&D` | Entregue e descarregado pelo motorista. | «Entregue» |
| `not delivered - self collection` | Não entregue: assinalado para levantamento no depósito, embora ainda possa sair em distribuição. Confirma com a filial Raben indicada no seguimento antes de lá ires. | «Pronta para levantamento» |
| `not delivered - consignee not available` | O motorista não te encontrou. | «Tentativa de entrega» |

**Outras entregas falhadas.** `not delivered - delivery refused`, `not delivered - wrong address`, `not delivered - truck with lift required` (requer camião com plataforma elevatória) e `not delivered - wrong/missing docs` indicam o motivo. O Peek mostra «Tentativa de entrega».

**Impedimentos.** `not delivered - breakdown lorry` e `not delivered - strike` são atrasos na estrada; `not delivered - at sender disposal` indica que a Raben retém o envio à espera de instruções do remetente; `cancelled` significa transporte cancelado. O Peek mostra «Problema reportado».

**Entregue com observação.** `delivered - damages`, `delivered - discrepancies`, `delivered - partly returned` e `delivered with delay` confirmam a entrega indicando o incidente. O Peek mostra «Entregue».

**Alterações de plano.** `new delivery date agreed with consignee` e `shipment parameters updated` alteram uma data ou dados: a mercadoria não se mexeu.

**Alfândega.** Um envio sujeito a alfândega apresenta passos como `customs: clearance in progress` e `customs clearance completed`; `customs: lack of invoices / incomplete invoices` significa falta de faturas, pelo que deves falar com a loja. Consulta [encomendas retidas na alfândega](guide:customs) e [significado dos estados](guide:tracking-statuses).

## Como a Raben entrega: paletes, descarga e prazos

As perguntas frequentes sobre paletes da Raben Polónia explicam como decorre a entrega. Os extras são reservados pelo cliente da Raben, a empresa que contratou o transporte (em regra a loja):

- **Descarga.** A descarga cabe ao destinatário: a Raben não é responsável por ela nem por disponibilizar empilhador ou rampa. O cliente pode contratar descarga manual pelo motorista (HUN) ou descarga no local de entrega (UNLO). Num artigo pesado, pergunta à loja o que contratou.
- **Prazos.** Na Polónia uma palete demora um dia útil como padrão, ou dois conforme o serviço contratado; no estrangeiro depende do país de destino. O cliente pode ainda reservar dia fixo (FIX), entrega até às 8:00, 10:00, 12:00 ou 16:00 (ND 08 a ND 16), ou dia e hora combinados (WXACT).
- **Se não estiveres.** Quando uma entrega falha por causa do destinatário, a Raben pode cobrar ao cliente.
- **Fotos de entrega.** Com o Picture Confirming Delivery (PCD), serviço gratuito escolhido pelo cliente, não precisas de estar presente. O motorista deixa a mercadoria onde pediste e fotografa a etiqueta, o envio e ambos juntos; as fotos servem de guia de entrega.

## Envio Raben atrasado, danificado ou não entregue

**Sem movimento.** Se o histórico parar durante dias, pergunta à loja ou à filial Raben indicada no seguimento. Consulta [seguimento que não atualiza](guide:tracking-not-updating).

**Atrasado.** Na UE, o vendedor tem de entregar em 30 dias salvo outro prazo acordado, lembra o portal europeu Your Europe. Passado esse prazo podes dar um prazo adicional e depois cancelar e pedir reembolso.

**Danificado ou perdido.** O vendedor é responsável por danos desde a expedição até receberes a mercadoria, e o Your Europe recomenda contactar primeiro o vendedor. Vê [encomenda danificada](guide:damaged-parcel) e [encomenda perdida](guide:lost-parcel). Regras da própria Raben:

- **Polónia.** A Raben Logistics Polska aceita reclamações por escrito do remetente ou do destinatário: através do myClaim, formulário online sem conta myRaben, ou por correio, fax ou e-mail. Regista danos visíveis na entrega e comunica danos ocultos em 7 dias após a entrega (no transporte internacional não contam domingos nem feriados). A Raben responde em 30 dias após receber toda a documentação. Um envio nacional considera-se perdido a partir do 31.º dia após a data prevista de entrega.
- **Alemanha.** A Raben Alemanha pede que verifiques a embalagem e a integridade na entrega e faças registar detalhadamente danos visíveis na guia de entrega assinada pelo motorista. Comunica os danos por escrito à filial Raben responsável; danos ocultos assim que detetados, no máximo 7 dias após a entrega.

## Contacto e apoio ao cliente Raben

Contactos indicados nos sites da Raben:

- **Contacto do teu envio.** A Raben pede para contactares a pessoa dedicada indicada na página de estado do envio, que o Peek não apresenta.
- **Horários.** O apoio ao cliente da Raben Polónia funciona de segunda a sexta, das 8:00 às 16:00 (hora da Polónia), procura responder em 3 horas e atende em polaco por defeito.
- **Filiais.** A [página de contacto](https://www.raben-group.com/contact) da Raben localiza depósitos por código postal e lista filiais por país; cada site nacional, como polska.raben-group.com, tem os seus próprios contactos.

## Seguir um envio Raben com o Peek

O formato de 15 dígitos é partilhado com a [BRT](carrier:brt) e outras transportadoras, pelo que o [Peek](/) não identifica a Raben só pelo número. Cola-o na mesma: o Peek segue mais de 4500 transportadoras. Ao adicioná-lo, o Peek pergunta à Raben e à BRT e, em último recurso, à Ozon se nenhuma das duas o conhecer («A verificar serviços de seguimento…») e mostra «Raben tem esta encomenda.» quando o seguimento Raben conhece o número; se várias o conhecerem, escolhes tu. Se nenhuma o encontrar ainda, aparece «Ainda sem histórico de seguimento» enquanto o Peek continua a verificar, ou «À espera de Raben» depois de escolheres Raben. Para definir a transportadora, carrega em «Alterar» e escolhe «Raben» em «Corresponde a este número», ou pesquisa (também encontra por «Raben Group»). Numa encomenda já adicionada, usa «Alterar transportadora».

Um link de seguimento de myraben.com ou oftc.myraben.com (…/link/ShipmentInformation?ShipmentNumber=…) associa logo o envio à Raben. Se o teu número Raben tiver outro formato, escolhe Raben manualmente: o Peek consulta o seguimento Raben com qualquer código de 4 a 40 letras e dígitos com pelo menos um dígito.

O Peek lê o seguimento Raben em inglês: estado, cada passo no texto da Raben com localidade e país, dia previsto de entrega (data sem hora) ou — assim que a rota do motorista define uma — janela horária com horas, peso e hora de entrega. As horas aparecem no teu fuso horário («Horas no teu fuso horário:»). Passos que alteram apenas data ou dados mantêm o estado anterior. O Peek omite número de volumes, volume, barra de progresso, observações e contacto da filial. O seguimento público não indica nomes ou moradas de remetente ou destinatário, ponto de levantamento, serviço nem prova de entrega.

O Peek consulta diretamente a Raben até de 10 em 10 minutos, e até de 2 em 2 minutos no dia em que a Raben regista o envio em distribuição — a partir das 6:00 (hora do país do seu último registo) ou meia hora antes de abrir uma janela horária prevista — enquanto o envio estiver aberto no Peek ou no dia da entrega com as notificações ligadas. Após dois dias sem novidades (12 horas enquanto ainda mostrar «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora a hora; após sete dias sem novidades em «Etiqueta criada», de 6 em 6 horas. Para as notificações, podes escolher «Todos os registos», «Passos importantes» ou «Só a entrega». Após quatro dias sem registos num envio em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek mostra «Sem atualizações desde…» e explica que «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Raben.». O Peek não pode contactar a Raben nem a loja por ti, reservar descarga nem abrir reclamação.

## Perguntas frequentes sobre o seguimento Raben

### O que é o Raben Group?

Um grupo logístico familiar europeu fundado em 1931 em Winterswijk, nos Países Baixos, e sediado em Robakowo perto de Poznań, na Polónia. Transporta paletes e cargas parciais por estrada em 17 países, geralmente contratado pelo remetente.

### Como sigo um envio Raben?

Introduz o número de envio na caixa «Shipment tracking» em raben-group.com e carrega em «Search»: o seguimento Raben abre num novo separador. Ou cola o número ou link na caixa acima.

### O motorista da Raben descarrega a minha encomenda?

Segundo as regras da Raben Polónia, apenas se a empresa que reservou o transporte contratou esse serviço: a descarga cabe ao destinatário, e a descarga pelo motorista é um serviço extra (HUN ou UNLO). Um registo `delivered D&D` significa que o motorista entregou e descarregou.

### Quanto tempo demora a Raben a entregar?

Dentro da Polónia, um ou dois dias úteis consoante o serviço contratado pelo remetente; para o estrangeiro depende do país de destino. O seguimento Raben mostra a data prevista de entrega.

### Quem devo contactar por uma entrega Raben danificada?

Primeiro a loja: na UE o vendedor é responsável até receberes os bens. Na Polónia também podes reclamar diretamente por escrito à Raben Logistics Polska; comunica danos ocultos no prazo de 7 dias após a entrega.

:::sources
- [Raben Group: home page](https://www.raben-group.com/) – caixa de seguimento, «Search», novo separador, 17 países
- [Raben Group: Customer Zone](https://www.raben-group.com/customer-zone) – um número por envio, data prevista, contacto na página de estado
- [Raben Group: Contact](https://www.raben-group.com/contact) – pesquisa de depósitos, filiais por país
- [Raben Group: Road transport and international transport](https://www.raben-group.com/services/road-network) – grupagem
- [Raben Group: E-commerce](https://www.raben-group.com/e-commerce) – entregas para lojas online
- [Raben Group: Picture Confirming Delivery](https://www.raben-group.com/it-solutions/pcd) – serviço gratuito, fotos, guia de entrega
- [Raben Group: Raben Group in Europe](https://www.raben-group.com/about-us/raben-group-in-europe) – 1931, Winterswijk, empresa familiar, 17 países
- [Raben Group: Imprint](https://www.raben-group.com/imprint) – Robakowo perto de Poznań
- [Raben Poland: Pytania i odpowiedzi - Transport paletowy](https://polska.raben-group.com/strefa-klienta/pytania-i-odpowiedzi-transport-paletowy) – descarga, HUN, UNLO, prazos, FIX, ND, WXACT, taxa de entrega falhada, PCD, horário de apoio, polaco
- [Raben Poland: Strefa uczestnika przewozu](https://polska.raben-group.com/strefa-uczestnika-przewozu) – dados do destinatário vindos de quem contratou
- [Raben Logistics Polska: Claims](https://polska.raben-group.com/en/customer-zone/raben-logistics-polska/claims-raben-logistics-polska) – quem reclama, processo, danos visíveis e ocultos, perda, resposta em 30 dias
- [Raben Germany: Transportschäden – Wir helfen Ihnen](https://deutschland.raben-group.com/kundenbereich/reklamationen) – verificação na entrega, participação escrita, 7 dias
- [Your Europe: Your rights when shopping in the EU](https://europa.eu/youreurope/citizens/consumers/shopping/shopping-consumer-rights/index_en.htm) – 30 dias, responsabilidade do vendedor, contactar primeiro o vendedor
- [Universal Parcel Scraper: Raben](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/raben) – formato do número, links, referências, o que o seguimento mostra e o Peek lê, passos
:::

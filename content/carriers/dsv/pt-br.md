---
title: Rastreamento DSV: códigos, status e DB Schenker
description: Rastreie uma remessa DSV rodoviária, aérea, marítima ou da antiga DB Schenker: qual código usar, o que cada status indica, entrega, sinistros e contato.
slug: rastreamento-dsv
published: 2026-11-26
updated: 2026-11-26
---

A DSV é um grupo dinamarquês de transporte e logística que movimenta cargas por via rodoviária, aérea e marítima e, em 2025, adquiriu a Schenker (DB Schenker) da Deutsche Bahn. Em dsv.com, a caixa "Track your shipment" tem dois campos: "Track DSV shipment" abre o rastreador da DSV, o myDSV, e "Track Schenker shipment" abre o antigo rastreador da DB Schenker. Não é preciso ter conta. O código vem de quem contratou o transporte, geralmente o remetente ou a loja; cole-o no campo acima.

## Código de rastreamento DSV: como ele é e onde encontrar

A empresa que contratou a DSV tem o número: procure no e-mail de envio da loja ou na página do seu pedido, ou peça ao remetente. Veja mais em [como encontrar seu código de rastreamento](guide:find-tracking-number).

Enquanto a DSV integra a Schenker aos seus sistemas, mantém os dois rastreadores: uma remessa reservada com a Schenker pode constar apenas no rastreador da Schenker. Se um não retornar nada, tente o outro.

| Código (inventado) | O que é |
| --- | --- |
| `DEKLM482917365` | 14 caracteres: código de país europeu como DE, SE, DK ou PL, três letras e nove dígitos. Código rodoviário da antiga DB Schenker e o formato atribuído à DSV no catálogo do Peek. |
| `KLM4829173` | Três letras e sete ou oito dígitos: outro código rodoviário da antiga DB Schenker. O Peek não identifica a DSV por esse formato. |
| `094827361549283711` | 18 dígitos (20 com `00` na frente): código de barras de um volume de remessa rodoviária da antiga DB Schenker. |
| Letras e dígitos | Número de remessa, reserva ou conhecimento filho (house bill) do myDSV. O Peek consulta o myDSV com qualquer sequência de 5 a 40 letras e dígitos que tenha ao menos um dígito. |
| `mydsv.com/track-shipment?query=DEKLM482917365` | Link para o rastreador do myDSV, como o botão "Track DSV shipment" de dsv.com o abre. |

Uma referência do cliente ou do pedido, ou o conhecimento aéreo máster (master air waybill) da companhia aérea, pode localizar a remessa na busca do myDSV, mas o Peek não acompanha esses códigos: use o número de remessa, reserva ou house bill da DSV. O Peek também não consegue escolher uma única remessa quando o mesmo número corresponde a várias.

## O que significam os status de rastreamento da DSV

O myDSV registra cada leitura da remessa, da reserva até o comprovante de entrega. Sua visualização pública traz informações resumidas; a visualização privada, mediante solicitação, mostra os detalhes da remessa. Estas são as etapas do myDSV como o Peek as lê:

| A DSV diz | O que significa para você | Status no Peek |
| --- | --- | --- |
| `Booking` | A DSV recebeu a reserva, com data mas sem horário. | "Etiqueta criada" |
| `Creation` | A remessa foi criada no sistema rodoviário da DSV. | "Etiqueta criada" |
| `Actual pickup` | A DSV coletou a remessa com o remetente. | "Recebida pela transportadora" |
| `Cargo received` | A carga chegou à DSV, em um terminal ou após a coleta. | "Recebida pela transportadora" |
| `Actual Departure`, `Actual departure - Origin Port` | Saiu de um porto ou aeroporto no trecho principal. | "Em trânsito" |
| `Actual arrival`, `Actual arrival- Destination Port` | Chegou a um porto ou aeroporto no trecho principal. | "Em trânsito" |
| `Handover responsibilty of cargo`, `Handover of Cargo` | A DSV entregou a carga ao final do seu serviço, encerrando a remessa (o primeiro mantém a grafia da DSV). | "Entregue" |
| `Actual delivery` | Entregue. | "Entregue" |

`Estimated Departure`, `Estimated arrival`, `Estimated pickup`, `Estimated delivery`, `Requested pickup` e `Requested delivery` são previsões, não leituras reais: o Peek não os coloca no histórico e exibe `Estimated delivery` como dia previsto ou faixa de dias até a entrega.

O antigo rastreador da DB Schenker cobre remessas rodoviárias com texto próprio:

| O rastreador DB Schenker diz | O que significa para você | Status no Peek |
| --- | --- | --- |
| `Booked` | O remetente reservou o transporte. | "Etiqueta criada" |
| `Out for collection` | Um motorista está a caminho do remetente para coleta. | "Etiqueta criada" |
| `Collected` | Coletada com o remetente. | "Recebida pela transportadora" |
| `Delivered to terminal by shipper` | O remetente levou a carga até um terminal. | "Recebida pela transportadora" |
| `Arrived`, `Departed` | Chegou a um terminal ou saiu dele. | "Em trânsito" |
| `Customs clearance initiated` | O desembaraço aduaneiro começou. | "Na alfândega" |
| `Customs clearance finalised` | Liberada na alfândega e novamente em movimento. | "Em trânsito" |
| `Out for Delivery` | No caminhão de entrega. | "Saiu para entrega" |
| `Not delivered` | A entrega não foi realizada; pode haver um motivo em seguida. | "Tentativa de entrega" |
| `To consignee's disposal`, `Ready for pick up` | Aguarda você retirá-la. | "Pronta para retirada" |
| `Delivered` | Entregue. | "Entregue" |
| `Picked up by consignee`, `Picked up at agent` | Retirada pelo destinatário ou em um agente. | "Entregue" |
| `ePOD available` | O comprovante eletrônico de entrega está pronto. | "Entregue" |

Pelo caminho, também aparecem `Linehaul departed`, `Linehaul arrived`, `Terminal inventory`, `Vessel Loaded`, `Vessel Unloaded`, `Train Loaded`, `Train Unloaded`, `Vessel/Train Loaded` e `Vessel/Train Unloaded`: todos como "Em trânsito" no Peek. `Not Collected` (coleta não realizada) e `Not loaded` (perdeu o carregamento previsto, com motivo) aparecem como "Precisa de atenção". Quando o rastreador inclui um motivo ao lado de uma etapa, como a razão de uma entrega não realizada, o Peek o acrescenta após o texto. Veja mais sobre cada fase em [status de rastreamento](guide:tracking-statuses).

## Como a DSV entrega

**Paletes e cargas fracionadas.** A DSV descreve sua rede rodoviária de grupagem como a maior da Europa: mais de 36 países, mais de 400 terminais e remessas de até 2.500 kg; assim, um palete ou pedido volumoso pode chegar de caminhão saindo de um terminal da DSV.

**Pedidos online.** O serviço Parcel E-commerce da DSV coleta encomendas de lojas, consolida os volumes entre fronteiras e utiliza redes locais de última milha para a entrega, então outra transportadora pode entregar o seu pedido. Se o e-mail da loja citar outra empresa ou informar um segundo código, rastreie-o também: a consulta da DSV no Peek não informa a próxima transportadora.

**Avisos e comprovante de entrega.** No myDSV, os usuários podem configurar notificações, por exemplo para coleta ou desembaraço aduaneiro, ver horários estimados de chegada de cargas rodoviárias e baixar o comprovante de entrega após a conclusão. Se não foi você quem contratou a DSV, fale com o remetente. As páginas da DSV não informam quantas tentativas de entrega são feitas nem por quanto tempo um terminal guarda a carga: pergunte ao escritório da DSV responsável pela entrega.

**Alfândega.** Na maioria dos países onde tem escritórios, a DSV é despachante aduaneira licenciada; nos demais, atua com agentes. Para ajuda no desembaraço, a DSV orienta entrar em contato com a DSV daquele país. Na União Europeia, compras online vindas de fora da UE pagam imposto aduaneiro de €3 por tipo de produto em pedidos de até €150, desde 1º de julho de 2026, e taxa de manuseio da União de €2 por tipo de produto, independentemente do valor, desde 1º de novembro de 2026. Fora da UE, valem as regras do seu país: veja [encomendas retidas na alfândega](guide:customs).

## Remessa DSV atrasada, parada ou danificada

**Sem movimentação.** Se um rastreador parar, tente o outro em dsv.com: uma remessa reservada com a Schenker pode ainda não estar no myDSV. Depois, pergunte ao remetente, que contratou a DSV e tem o contato direto. Veja [rastreamento que não atualiza](guide:tracking-not-updating).

**Carga danificada ou faltando.** Em compras online, comece pela loja que contratou a DSV. O eClaims da DSV recebe pedidos de indenização e documentos, mostra o andamento e coloca você em contato com os reguladores locais de sinistros; a página não indica prazo, então não espere. Veja [encomenda danificada](guide:damaged-parcel) e [encomenda extraviada](guide:lost-parcel).

**Uso indevido do nome DSV.** A DSV alerta que empresas e pessoas sem vínculo com ela às vezes usam seu nome para vender serviços de frete. Se não tiver certeza de que um site ou contato é realmente da DSV, confirme com o escritório local da DSV; sobre mensagens falsas de entrega, veja [golpes por SMS de entrega](guide:delivery-scam-texts).

## Contato e atendimento ao cliente da DSV

Em dsv.com:

- **Formulário de contato:** na [página de ajuda e contato](https://www.dsv.com/en/support/help-and-contact) da DSV.
- **Escritórios locais:** a DSV atua em mais de 90 países; [encontre seu escritório local da DSV](https://www.dsv.com/en/support/find-dsv) por país.
- **Sinistros:** pelo eClaims, a partir da [página de Claims](https://www.dsv.com/en/support/self-services/claims) da DSV.
- **DSV A/S:** Hedehusene, Dinamarca, +45 43 20 30 40, conforme seu aviso legal. Não é um telefone de rastreamento.

As páginas de ajuda da DSV não informam telefone para rastreamento.

## Acompanhe uma remessa DSV com o Peek

Cole um código de 14 caracteres como `DEKLM482917365` e o Peek consulta a DSV enquanto você o adiciona ("Consultando serviços de rastreamento…"), exibindo "DSV está com esta encomenda." quando a DSV o encontra. Se a DSV ainda não o listar, o status aparece como "Aguardando a primeira movimentação de DSV", marcado como "Provavelmente DSV", enquanto o Peek continua consultando; após uma semana, o Peek pede que você confira o código ou escolha outra transportadora.

O Peek não consegue identificar a DSV por nenhum outro formato de número. Cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Para selecionar a DSV manualmente, toque em "Trocar" e busque por DSV; "Schenker" também encontra ("Também chamada de DB Schenker"). Em uma encomenda já adicionada, use "Trocar transportadora". Um link de rastreamento de mydsv.com classifica a remessa em DSV na hora, assim como um link do rastreador Schenker em dsv.com com `refNumber=`.

O Peek consulta o myDSV primeiro. Um código rodoviário ou código de barras de volume da antiga DB Schenker que o myDSV não reconhece vai para o rastreador da Schenker, que o Peek lê apenas para remessas rodoviárias: para uma remessa aérea ou marítima encontrada só lá, use "Track Schenker shipment" em dsv.com.

O Peek exibe o status, cada etapa no texto da DSV com cidade e país, e o dia previsto de entrega. A DSV fornece o horário local de cada leitura sem fuso horário; o Peek estima o fuso pelo local da leitura (cidade, porto, código de aeroporto) ou pelas leituras vizinhas e, se não conseguir, usa o horário de Copenhague, o que às vezes pode divergir. Quando a DSV informa o nome do destinatário, ele aparece em "Destinatário" na página privada da sua encomenda, e a cidade do endereço de entrega pode fechar a rota do seu mapa enquanto a remessa está a caminho; links compartilhados não exibem nenhum dos dois. O Peek omite o nome do serviço, a cidade do remetente, o endereço de rua, os códigos postais e a barra de progresso da DSV, e do rastreador Schenker omite o remetente, as referências, a descrição da carga e o nome de quem assinou.

Se a Early Bird, empresa sueca de entregas, repassar sua encomenda à DSV ou à DB Schenker, o Peek indica a DSV como a transportadora entregadora e passa a acompanhar a DSV assim que surgem novas leituras lá.

Enquanto a remessa estiver aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek verifica até a cada 10 minutos, e a cada 2 minutos a partir das 6h no horário local no dia em que a DSV registra a saída para entrega. Você pode receber avisos para "Cada movimentação", "Etapas importantes" ou "Só a entrega". Após dois dias sem novidades (12 horas enquanto a remessa ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), o Peek verifica a cada hora, e a cada 6 horas após sete dias sem novidades em "Etiqueta criada". Se a remessa passar quatro dias sem nova linha em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek mostra "Sem atualizações desde …" com o dia dessa linha e o link "Pedir ajuda a DSV". O Peek não pode contatar a DSV nem o remetente por você, agendar entregas nem abrir pedidos de indenização.

## Perguntas sobre o rastreamento DSV

### A DSV é a mesma empresa que a DB Schenker?

Sim. A DSV, fundada em 1976, concluiu a aquisição da Schenker junto à Deutsche Bahn em 30 de abril de 2025, e dbschenker.com foi integrado a dsv.com. Por enquanto, as remessas da Schenker mantêm seu próprio rastreador.

### Como rastrear uma remessa da DB Schenker agora?

Digite o código em "Track Schenker shipment" na caixa "Track your shipment" de dsv.com, ou cole-o no campo acima; a menos que tenha 14 caracteres começando com um código de país, selecione DSV manualmente como transportadora. O Peek consulta o myDSV e, para remessa rodoviária, o rastreador da Schenker.

### O que significa handover of cargo no rastreamento da DSV?

A DSV repassou a carga ao final do seu serviço e a remessa está concluída; o Peek exibe "Entregue". Se nada chegou até você, pergunte ao remetente onde a DSV fez a entrega e veja [encomenda marcada como entregue mas não recebida](guide:delivered-not-received).

### A DSV tem telefone de atendimento ao cliente?

As páginas de ajuda não informam telefone para rastreamento. Use o formulário de contato ou encontre seu escritório local da DSV em dsv.com. O número do aviso legal da DSV A/S, +45 43 20 30 40 na Dinamarca, não é uma linha de rastreamento.

:::sources
- [DSV: Book and track shipments with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track) – rastreamento sem conta, dois campos de busca, fusão de dbschenker.com em dsv.com
- [DSV: myDSV](https://www.dsv.com/en/digital-solutions/mydsv) – visualização pública e privada, histórico de leituras, notificações, comprovante de entrega
- [DSV: Self-services](https://www.dsv.com/en/support/self-services) – rastreadores DSV e Schenker, eClaims, sistemas Schenker até a migração
- [DSV: Frequently asked questions](https://www.dsv.com/en/support/faq) – formulário de contato, duas versões do myDSV durante a transição
- [DSV: Help and contact](https://www.dsv.com/en/support/help-and-contact) – mais de 90 países, localizador de escritórios
- [DSV: Find your local DSV office](https://www.dsv.com/en/support/find-dsv) – escritórios por país
- [DSV: Claims](https://www.dsv.com/en/support/self-services/claims) – funcionamento do eClaims
- [DSV: Groupage transport](https://www.dsv.com/en/our-solutions/modes-of-transport/road-transport/groupage) – mais de 36 países, mais de 400 terminais, até 2.500 kg
- [DSV: E-commerce delivery solutions](https://www.dsv.com/en/our-solutions/contract-logistics/ecommerce-solutions/last-mile-delivery) – consolidação internacional, redes locais de última milha
- [DSV: Customs clearance](https://www.dsv.com/en/our-solutions/modes-of-transport/value-added-services/customs-clearance) – despachante licenciada, agentes, contato com a DSV do país
- [DSV: History](https://www.dsv.com/en/about-dsv/history) – fundada em 1976, rodoviário, aéreo e marítimo, compra da Schenker junto à Deutsche Bahn em 2025
- [DSV: DSV completes acquisition of Schenker](https://www.dsv.com/en/about-dsv/press/news/dsv-completes-acquisition-of-schenker-2628130) – aquisição concluída em 30 de abril de 2025, integração
- [DSV: Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) – DSV A/S na Dinamarca, telefone, uso indevido do nome DSV
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto aduaneiro de €3 por tipo de produto até €150, taxa de manuseio da União de €2
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 por tipo de produto independentemente do valor
- [Universal Parcel Scraper: DSV](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv) – formatos de código e links, dois rastreadores, textos e etapas da DSV, horários locais, o que o Peek lê
:::

---
title: Seguimento nox NachtExpress: estados, depósito e contacto
description: Acompanha um envio nox NachtExpress: formato do código de barras, significado dos estados em alemão, entrega noturna em local acordado e contactos.
slug: seguimento-nox
published: 2026-11-23
updated: 2026-11-23
---

A nox (nox NachtExpress) é uma empresa alemã de expresso noturno, nox Germany GmbH, sediada em Langenfeld. Transporta peças sobressalentes e outras mercadorias urgentes para empresas. Faz a recolha à tarde e entrega durante a noite, por regra antes das 8:00 (hora alemã), num local acordado com o destinatário: um cofre, uma garagem, uma oficina ou a bagageira de uma carrinha de assistência. Acompanha um envio pelo código de barras no [seguimento da nox](https://trackandtrace.app.nox.express/scannungen) ou cola o número na caixa acima. O site está em alemão: as tabelas abaixo explicam-no.

## Código de barras nox: formato e onde encontrar

A nox chama «barcode» ao seu número de seguimento. Cada volume tem de levar uma etiqueta nox criada pelo remetente ao reservar o envio, pelo que o remetente também tem o código de barras. Não tens número? Pergunta ao remetente ou vê [como encontrar o teu número de seguimento](guide:find-tracking-number). Formatos no código do Peek:

| Código de barras (inventado) | O que é |
| --- | --- |
| `220004829173640715482917` | 24 dígitos começados por 22000: código de cliente, 000, número de cliente do remetente, data de impressão da etiqueta e número de série. O catálogo do Peek conhece este formato. [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost) e [Correios da Áustria](carrier:austrian-post) também usam 24 dígitos. |
| `SWR482917364058291736405` | SWR e 21 dígitos: um código de barras nox mais antigo. |
| `RA1482917364058482917364` | RA, seguido de 1 ou 2 (tipo de recolha), 12 letras ou dígitos para os clientes e série de 9 dígitos: código de barras de devolução. |
| `C48291736405829173640582917364` | C e 29 dígitos: código de barras próprio do remetente, ao qual o seguimento da nox também responde. |
| `trackandtrace.app.nox.express/scannungen/SWR482917364058291736405` | Ligação para um envio no seguimento da nox. Cola a ligação inteira no Peek. |

Outros códigos de barras nox têm 24 dígitos com outro código de cliente de dois dígitos antes de 000. No Peek, os espaços não importam e minúsculas também funcionam.

## Estados de seguimento nox explicados

O seguimento da nox apresenta cada registo com `Status`, `Scanort` (local), `Bemerkung` (observação), `Datum` (data) e `Uhrzeit` (hora). Na terminologia da nox, Collo designa um volume do teu envio e Niederlassung uma filial nox.

| A nox indica | O que significa para ti | Estado no Peek |
| --- | --- | --- |
| `Collo durch Versender bereitgestellt zur Abholung` | O remetente preparou o volume para recolha pela nox. A nox ainda não o tem. | «Etiqueta criada» |
| `Collo in der nox Niederlassung eingetroffen` | O volume chegou a uma filial da nox. | «Em trânsito» |
| `Collo von der nox Niederlassung weiterverladen` | A filial carregou-o para outra filial. | «Em trânsito» |
| `Collo steht am Lager in nox Niederlassung, Zustellung nicht im geplanten Zeitfenster` | Está guardado na filial e não foi entregue na janela prevista. Sairá numa noite posterior. | «Em trânsito» |
| `Collo in nox Zustellfahrzeug geladen` | Está carregado no veículo de entrega. | «Em distribuição» |
| `Collo von nox zugestellt` | Entregue: geralmente deixado no local de depósito acordado, sem assinatura. | «Entregue» |

Se o site mostrar `Der von Ihnen angegebene Barcode ist uns nicht bekannt.`, a nox não reconhece esse código de barras: confirma-o com o remetente. Para mais pormenores ou reclamar, o site pede que valides o código postal do destinatário em `PLZ validieren/Reklamation erfassen`.

## Como a nox entrega: à noite, no teu local de depósito

**Quando.** A nox costuma recolher no remetente entre as 14:00 e as 18:00 (hora alemã). Nos termos da nox, o NachtExpress entrega no dia seguinte, de terça a sábado incluindo feriados, por regra antes das 8:00 (hora alemã). O remetente pode reservar entrega antes das 7:00 ou das 6:00 (hora alemã). A nox também vende entregas aos fins de semana e feriados como serviço separado (WochenendService). Para volumes com mais de 100 kg, ou mais de 3,20 m de comprimento, 1,20 m de largura ou 1,50 m de altura, o horário pode variar. Envios para ilhas do Mar do Norte e do Báltico não têm garantia de horário.

**Onde.** Como a nox entrega enquanto muitos clientes ainda dormem, deixa o envio num local de depósito acordado antecipadamente, geralmente sem assinatura. Exemplos dados pela nox:

- uma caixa separada nas instalações da tua empresa;
- oficina, sala de lavagem, sala de pausa ou garagem;
- a bagageira da viatura de um técnico de assistência;
- o porteiro, que recebe em mão do motorista;
- uma das estações de serviço nox com acesso 24 horas, como um posto de combustível aberto todo o dia.

**Definir o local de depósito.** Regista ou atualiza a qualquer momento, com fotos e descrição, na ferramenta online [ready2nox](https://ready2nox.de/). Segundo os termos da nox, é a única forma de o definir, e mantém-se válido até o alterares lá. Assim que o volume fica nesse local, conta como entregue.

**Sem local de depósito.** Nos termos da nox, a empresa pode deixá-lo na tua morada. Se o risco de perda for claramente demasiado elevado, pode não entregar e devolver ao remetente.

**Levantamento na filial.** Como serviço extra, podes levantar os envios na filial nox, onde ficam guardados para ti.

**Prova.** Quando não está ninguém, a nox fotografa o volume e o sítio onde ficou, guardando as fotos como prova. Pode partilhar uma foto caso a caso com quem demonstre interesse legítimo, mas não existe direito legal a obtê-la.

**Fora da Alemanha.** A nox atua noutros 20 países europeus através de empresas irmãs e parceiros. Enviar para o estrangeiro requer acordo prévio do remetente com a nox, e as regras de reclamação podem variar por país.

## Envio nox atrasado, danificado ou desaparecido

Para substituição, o teu contacto é a empresa que enviou a mercadoria.

**Sem movimento.** Se `Collo durch Versender bereitgestellt zur Abholung` continua a ser o único registo, a nox ainda não recolheu o volume: pergunta ao remetente. Se aguarda na filial, sairá numa noite posterior (ver tabela acima). Em caso de atraso, usa o seguimento da nox: valida o código postal e escolhe `Laufzeitverzug` (atraso) ou `Sonstige Anliegen` (outros assuntos). Vê também [porque o seguimento deixa de atualizar](guide:tracking-not-updating).

**Entregue mas não está lá.** Verifica primeiro o local de depósito acordado e depois outros espaços usados pela tua empresa. Pergunta ao porteiro ou ao técnico cujo veículo pode tê-lo recebido. Depois avisa rapidamente a nox antes do prazo abaixo. Mais em [entregue mas não recebida](guide:delivered-not-received).

**Danificado.** No NachtExpress, a nox tem de receber qualquer reclamação ou participação de danos até às 12:00 (hora alemã) do dia da entrega, por escrito e com descrição. Se esse dia for sábado, domingo ou feriado, o prazo passa para as 12:00 (hora alemã) do dia útil seguinte. Uma reserva genérica não basta, e após o prazo os termos da nox excluem responsabilidade. Guarda a mercadoria na embalagem original para a nox poder inspecionar e recolher, ou a nox não assume responsabilidade. Comunica no seguimento após validar o código postal (`Reklamation einer Beschädigung`), pelo formulário de contacto ou no portal de clientes. Vê [encomendas danificadas](guide:damaged-parcel).

**Alfândega.** As páginas da nox mencionam a alfândega apenas como serviço aos clientes para importações e exportações. Pergunta ao remetente e vê [encomendas retidas na alfândega](guide:customs).

## Contacto e apoio ao cliente nox

O Peek não pode contactar a nox nem o remetente por ti. Contactos publicados nos sites da nox:

- **Apoio ao cliente:** +49 (0) 4224 920 000, segunda a sexta das 7:00 às 17:00 (hora alemã), ou customerservice@nox-nachtexpress.de, tal como indicado no seguimento da nox.
- **Formulário de contacto:** [formulário de contacto da nox](https://www.nox.eu/de/de/kontakt/kontaktformular/), para dúvidas e reclamações.
- **Reclamações:** no seguimento após validar o código postal. Os formulários (atraso, danos, outros) perguntam se és destinatário (Empfänger), remetente (Versender) ou terceiro contratante.
- **Empresa:** nox Germany GmbH, Katzbergstr. 3, 40764 Langenfeld, Alemanha; welcome@nox.eu.

Para acompanhar todos os seus envios, uma empresa precisa de conta no portal de clientes nox: solicita-a pelo formulário de contacto.

## Seguir um envio nox com o Peek

O Peek não consegue identificar a nox apenas pelo número: os códigos de barras `22000` têm o mesmo comprimento que Ciblex, InPost, bpost e Correios da Áustria, e o Peek não associa os outros códigos à nox pela forma. Cola-o na mesma: o Peek acompanha mais de 4500 transportadoras. Ao adicionar um código `22000`, o Peek pergunta às transportadoras com esse formato, primeiro as do teu país («A verificar serviços de seguimento…»), e mostra «nox tem esta encomenda.» quando o seguimento da nox o reconhece. Se várias o reconhecerem, escolhes tu.

Se nenhuma responder ainda, aparece «Ainda sem histórico de seguimento» enquanto o Peek continua a verificar, ou «À espera de nox» depois de escolheres nox. Para escolher manualmente, carrega em «Alterar» e seleciona nox: aparece em «Corresponde a este número» para códigos `22000`; para códigos SWR, RA ou C, pesquisa por ela (NOX Nachtexpress, Innight Express e TNT Innight também a encontram). Num envio já adicionado usa «Alterar transportadora». Se escolheres nox para um número de outro formato, o Peek indica que «não emite números como este» e não consulta a nox: depois de adicionado, salvo se um serviço de seguimento tiver o envio, mostra «Verifica o número de seguimento». Uma ligação do seguimento da nox, em `trackandtrace.app.nox.express` ou `tcs.app.nox.express`, associa de imediato o envio à nox.

O Peek lê o estado, o texto alemão e a hora de cada registo (em hora alemã) e a hora de entrega. Omite o local e a observação do registo e não pede código postal, pelo que não mostra o que o site revela após validar o código postal. O Peek não mostra data prevista de entrega, peso, local de depósito, foto de entrega nem quem assinou.

De dia e de noite, enquanto manténs o envio aberto no Peek ou no dia da entrega com as notificações ativadas, o Peek verifica até de 10 em 10 minutos, e a partir das 6:00 (hora alemã) do dia em que a nox regista o envio em distribuição até de 2 em 2 minutos enquanto o site próprio da nox responder. Após dois dias sem novidades (12 horas enquanto o envio continuar em «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora em hora; e se ficar em «Etiqueta criada» durante sete dias, de 6 em 6 horas.

Se um envio ainda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» passar quatro dias sem novo registo, o Peek assinala-o com «Sem atualizações há quatro dias» (ou «Sem atualizações desde …»), explica: «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a nox.» e mostra «Pede ajuda a nox». O Peek pode notificar-te de «Todos os registos», «Passos importantes» ou «Só a entrega». Não pode definir o local de depósito nem abrir uma reclamação por ti.

## Perguntas sobre o seguimento nox

### O que é a nox NachtExpress?

O serviço expresso noturno da nox Germany GmbH, de Langenfeld. Mercadorias recolhidas até ao início da noite chegam ao destinatário antes do arranque do trabalho no dia útil seguinte. Os seus setores principais são automóvel, veículos comerciais, máquinas de construção e agricultura, com soluções específicas para saúde.

### Quando entrega a nox?

Durante a noite, por regra até às 8:00 (hora alemã), de terça a sábado incluindo feriados. O remetente pode contratar entrega antes das 7:00 ou das 6:00 (hora alemã).

### Onde deixa a nox o meu envio?

No local de depósito acordado para a tua morada, definido na ferramenta ready2nox da nox. Sem local definido, a nox pode deixá-lo na tua morada.

### A nox precisa de assinatura?

Normalmente não: a nox entrega sem recibo, muitas vezes quando não está ninguém, e fotografa o volume onde foi deixado. Num porteiro, o motorista entrega em mão.

### Como comunico uma entrega nox danificada?

No NachtExpress, até às 12:00 (hora alemã) do dia da entrega, por escrito, no seguimento ou no formulário de contacto da nox. Guarda a mercadoria e a embalagem original. Se a entrega cair num fim de semana ou feriado, tens até às 12:00 (hora alemã) do dia útil seguinte.

:::sources
- [nox: Sendungsverfolgung/Reklamation erfassen](https://www.nox.eu/de/de/kundenbereich/sendungsverfolgung/) – formulário de código de barras que abre o seguimento da nox, portal de clientes para todos os envios
- [nox: Track & Trace](https://trackandtrace.app.nox.express/scannungen) – colunas do seguimento, código desconhecido, validação do código postal, formulários de reclamação, telefone, horário e e-mail do apoio ao cliente, ready2nox
- [nox: Hinweise zum Nachtversand](https://www.nox.eu/de/de/kundenbereich/hinweise-zum-nachtversand/) – recolha das 14:00 às 18:00, entrega antes das 8:00, locais de depósito, volumes grandes, outros 20 países, reclamações via formulário de contacto, prazo para danos
- [nox: NachtExpress](https://www.nox.eu/de/de/leistungen/nachtexpress/) – antes das 8:00, 7:00 ou 6:00, entrega sem recibo, exemplos de locais de depósito, levantamento na filial, serviço aduaneiro
- [nox: Abstellplatzmanagement](https://www.nox.eu/de/de/leistungen/abstellplatzmanagement/) – exemplos de locais de depósito, ready2nox com fotos e descrições
- [nox: Über uns](https://www.nox.eu/de/de/ueber-uns/) – entrega antes do início do trabalho, clientes a dormir, setores principais
- [nox: Allgemeine Geschäftsbedingungen, from 1 September 2026 (PDF)](https://cms.nox.eu/de/wp-content/uploads/sites/4/2026/09/Allgemeine-Geschaftsbedingungen-fur-Geschaftskunden-nox-DE-Sept-2026-Final.pdf) – etiquetas nox, dias de entrega, local de depósito no ready2nox, entrega sem local definido, fotos, ilhas, envios internacionais, prazo de reclamação, regras por país
- [nox: Allgemeine Geschäftsbedingungen](https://www.nox.eu/de/de/agb/) – termos gerais em vigor
- [nox: Kontakt](https://www.nox.eu/de/de/kontakt/kontaktformular/) – formulário de contacto
- [nox: Impressum](https://www.nox.eu/de/de/impressum) – nox Germany GmbH, Langenfeld, telefone, e-mail
- [Universal Parcel Scraper: nox](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/nox) – formatos de códigos de barras, ligações do seguimento, textos e etapas de registo, hora alemã, o que o Peek lê
:::

package com.plhery.peekdeliverytracker.core.catalog

import com.plhery.peekdeliverytracker.core.catalog.CatalogExpressions.matches

// CarrierCatalog's check digits, as the shared engine runs them. Each check reads its digits only once
// an ASCII pattern has proved them.

/**
 * The rule's check, as the shared engine runs it; nil for an id this build does not know, which a newer
 * downloaded catalog can name. Detection then keeps the rule's carrier as a low-confidence candidate that
 * is never preferred.
 */
fun CarrierCatalog.Companion.checksumPasses(checksum: String, number: String): Boolean? = when (checksum) {
    "mondial-relay" -> isValidMondialRelayBarcode(number)
    "s10" -> isValidS10(number)
    "hermes" -> isValidHermesParcelNumber(number)
    "gls" -> isValidGlsParcelNumber(number)
    "dhl-express" -> isValidDhlExpressWaybill(number)
    "tnt" -> isValidTntConsignmentNumber(number)
    "poczta-polska" -> isValidPocztaPolskaBarcode(number)
    "correos-spain" -> isValidCorreosSpainCheckLetter(number)
    "dpd" -> isValidDpdParcelNumber(number)
    "usps" -> isValidUspsPackageBarcode(number)
    "sscc" -> isValidSscc(number)
    "ups" -> isValidUpsTrackingNumber(number)
    "colissimo" -> isValidColissimoParcelNumber(number)
    "ukrposhta" -> isValidUkrposhtaBarcode(number)
    "evri" -> isValidEvriParcelNumber(number)
    "mod7" -> hasMod7CheckDigit(number)
    "gs1" -> hasGs1CheckDigit(number)
    "ontrac" -> isValidOnTracTrackingNumber(number)
    "luhn" -> hasLuhnCheckDigit(number)
    "fedex" -> isValidFedExTrackingNumber(number)
    "sf-express" -> isValidSfExpressWaybill(number)
    "fedex-ground-96" -> isValidFedExGround96Barcode(number)
    "fedex-1d" -> isValidFedEx1DBarcode(number)
    "identcode" -> isValidDhlIdentcode(number)
    "fedex-ground-economy" -> isValidFedExGroundEconomyNumber(number)
    "pos-laju" -> isValidPosLajuConsignment(number)
    "saia" -> isValidSaiaPro(number)
    "magyar-posta" -> isValidMagyarPostaNumber(number)
    "poste-italiane" -> isValidPosteItalianeRegisteredNumber(number)
    "frankier-id" -> isValidFrankierId(number)
    "dhl-ecommerce-pl" -> isValidDhlEcommercePolandNumber(number)
    else -> null
}

/** The ASCII digits of a string a pattern has proved. */
private fun String.digits(): IntArray = IntArray(length) { this[it] - '0' }

/** Mod 11 with weights 2 to 7 repeating from the right; a result of 10 or 11 becomes 0. */
private fun mod11CheckDigit(digits: List<Int>): Int {
    val sum = digits.reversed().withIndex().sumOf { (offset, digit) -> digit * (2 + offset % 6) }
    val remainder = 11 - sum % 11
    return if (remainder >= 10) 0 else remainder
}

fun CarrierCatalog.Companion.isValidMondialRelayBarcode(value: String): Boolean {
    if (!matches(value, "^[0-9]{26}$")) return false
    val digits = value.digits()
    fun check(range: IntRange): Int = mod11CheckDigit(digits.slice(range))
    val sequence = digits[10] * 10 + digits[11]
    val count = digits[12] * 10 + digits[13]
    return sequence > 0 && sequence <= count && check(0 until 14) == digits[14] && check(15 until 25) == digits[25]
}

/** Hermes Germany's legacy 14-digit numbers: a modulo-10 check digit weighted 3, 1, 3, … from the left. */
fun CarrierCatalog.Companion.isValidHermesParcelNumber(value: String): Boolean {
    if (!matches(value, "^[0-9]{14}$")) return false
    val digits = value.digits()
    val sum = (0 until 13).sumOf { digits[it] * (if (it % 2 == 0) 3 else 1) }
    return (10 - sum % 10) % 10 == digits[13]
}

/**
 * Deutsche Post's Identcode on DHL Paket's 12-digit numbers: a modulo-10 check digit weighted 4, 9, 4, …
 * from the left.
 */
fun CarrierCatalog.Companion.isValidDhlIdentcode(value: String): Boolean {
    if (!matches(value, "^[0-9]{12}$")) return false
    val digits = value.digits()
    val sum = (0 until 11).sumOf { digits[it] * (if (it % 2 == 0) 4 else 9) }
    return (10 - sum % 10) % 10 == digits[11]
}

/**
 * DHL eCommerce Poland's 11-digit parcel numbers: the Identcode's weights, 4, 9, 4, … from the left, over
 * ten digits and a modulo-10 check digit.
 */
fun CarrierCatalog.Companion.isValidDhlEcommercePolandNumber(value: String): Boolean {
    if (!matches(value, "^[0-9]{11}$")) return false
    val digits = value.digits()
    val sum = (0 until 10).sumOf { digits[it] * (if (it % 2 == 0) 4 else 9) }
    return (10 - sum % 10) % 10 == digits[10]
}

/** GLS 12-digit parcel numbers: a modulo-10 check digit weighted 3, 1, 3, … from the right, plus one. */
fun CarrierCatalog.Companion.isValidGlsParcelNumber(value: String): Boolean {
    if (!matches(value, "^[0-9]{12}$")) return false
    val digits = value.digits()
    val sum = 1 + digits.slice(0 until 11).reversed().withIndex()
        .sumOf { (offset, digit) -> digit * (if (offset % 2 == 0) 3 else 1) }
    return (10 - sum % 10) % 10 == digits[11]
}

/** Ukrposhta's 13-digit domestic barcode ends in the mod 11 check of its first twelve digits. */
fun CarrierCatalog.Companion.isValidUkrposhtaBarcode(value: String): Boolean {
    if (!matches(value, "^[0-9]{13}$")) return false
    val digits = value.digits()
    return mod11CheckDigit(digits.slice(0 until 12)) == digits[12]
}

/**
 * The number before the last digit, divided by seven, leaves the last digit: Yamato's 12-digit numbers, and
 * Blue Dart and Aramex 11-digit waybills alike.
 */
fun CarrierCatalog.Companion.hasMod7CheckDigit(value: String): Boolean {
    if (!matches(value, "^[0-9]{2,15}$")) return false
    val serial = value.dropLast(1).toLongOrNull() ?: return false
    return serial % 7 == (value.last() - '0').toLong()
}

/**
 * A twelve-digit Saia PRO: ten digits, a 0, then the remainder of the ten divided by seven. The check is
 * inferred from public PROs; one with another eleventh digit is not checked.
 */
fun CarrierCatalog.Companion.isValidSaiaPro(value: String): Boolean {
    if (!matches(value, "^[0-9]{10}0[0-6]$")) return false
    val ten = value.take(10).toLongOrNull() ?: return false
    return ten % 7 == (value.last() - '0').toLong()
}

/**
 * Magyar Posta's domestic numbers: `P`, a product letter, a three-character customer code, seven digits and
 * a check digit. The seven digits weigh 3, 1, 3, … from the left; the sum mod 10 is the last digit, with no
 * complement. The code is outside the check.
 */
fun CarrierCatalog.Companion.isValidMagyarPostaNumber(value: String): Boolean {
    if (!matches(value, "^P[A-Z][0-9A-Z]{3}[0-9]{8}$")) return false
    val digits = value.drop(5).digits()
    val sum = (0 until 7).sumOf { digits[it] * (if (it % 2 == 0) 3 else 1) }
    return sum % 10 == digits[7]
}

/**
 * Poste Italiane's registered mail: eleven digits and a check digit. Eleven times the sum of the digits in
 * even places, plus the sum of those in odd places, gives a number whose digit sum mod 10 is the last digit.
 */
fun CarrierCatalog.Companion.isValidPosteItalianeRegisteredNumber(value: String): Boolean {
    if (!matches(value, "^[0-9]{12}$")) return false
    val digits = value.digits()
    var odd = 0
    var even = 0
    for (offset in 0 until 11) {
        if (offset % 2 == 0) odd += digits[offset] else even += digits[offset]
    }
    val digitSum = (11 * even + odd).toString().digits().sum()
    return digitSum % 10 == digits[11]
}

/**
 * Deutsche Post's Frankier-ID: nineteen characters and a hexadecimal check character, the CRC-4 (polynomial
 * x⁴ + x + 1, from zero) of the nineteen as ASCII bytes, most significant bit first.
 */
fun CarrierCatalog.Companion.isValidFrankierId(value: String): Boolean {
    if (!matches(value, "^[0-9A-Z]{19}[0-9A-F]$")) return false
    val check = Character.digit(value.last(), 16)
    var crc = 0
    for (byte in value.take(19).toByteArray(Charsets.US_ASCII)) {
        for (bit in 7 downTo 0) {
            val carry = (crc shr 3) xor ((byte.toInt() shr bit) and 1)
            crc = (crc shl 1) and 0xF
            if (carry == 1) crc = crc xor 0x3
        }
    }
    return crc == check
}

/** GS1 mod 10: counted from the check digit, the digits before it weigh 3, 1, 3, … */
fun CarrierCatalog.Companion.hasGs1CheckDigit(value: String): Boolean {
    if (!matches(value, "^[0-9]{2,}$")) return false
    val digits = value.digits()
    var sum = 0
    for (offset in 0 until digits.size - 1) {
        sum += digits[digits.size - 2 - offset] * (if (offset % 2 == 0) 3 else 1)
    }
    return (10 - sum % 10) % 10 == digits[digits.size - 1]
}

/** Luhn over every digit, the last one being the check: Purolator's 12-digit PINs. */
fun CarrierCatalog.Companion.hasLuhnCheckDigit(value: String): Boolean {
    if (!matches(value, "^[0-9]{2,}$")) return false
    val digits = value.digits()
    var sum = 0
    for (offset in digits.indices) {
        val doubled = digits[digits.size - 1 - offset] * (if (offset % 2 == 0) 1 else 2)
        sum += if (doubled > 9) doubled - 9 else doubled
    }
    return sum % 10 == 0
}

/**
 * FedEx 12-digit numbers: the first eleven digits weigh 3, 1, 7, … from the left, and the sum mod 11 mod 10
 * is the last digit.
 */
fun CarrierCatalog.Companion.isValidFedExTrackingNumber(value: String): Boolean {
    if (!matches(value, "^[0-9]{12}$")) return false
    val digits = value.digits()
    val weights = intArrayOf(3, 1, 7)
    val sum = (0 until 11).sumOf { digits[it] * weights[it % 3] }
    return sum % 11 % 10 == digits[11]
}

/**
 * FedEx Ground's 22-digit `96` barcode ends in the 15-digit tracking number it carries; its GS1 mod 10 check
 * covers only those fifteen digits.
 */
fun CarrierCatalog.Companion.isValidFedExGround96Barcode(value: String): Boolean =
    matches(value, "^96[0-9]{20}$") && hasGs1CheckDigit(value.drop(7))

/**
 * FedEx Ground Economy's 20-digit number is a USPS package identifier without its `92` channel; its check
 * digit is the identifier's MOD10, computed with the `92` in front.
 */
fun CarrierCatalog.Companion.isValidFedExGroundEconomyNumber(value: String): Boolean =
    matches(value, "^[0-9]{20}$") && hasGs1CheckDigit("92$value")

/**
 * FedEx's 34-digit barcode carries a 12-digit FedEx number behind two zeros in positions 21–34; its own check
 * reduces there to the 12-digit check.
 */
fun CarrierCatalog.Companion.isValidFedEx1DBarcode(value: String): Boolean =
    matches(value, "^[0-9]{20}00[0-9]{12}$") && isValidFedExTrackingNumber(value.takeLast(12))

/** A digit keeps its value; a letter counts as its ASCII code minus 63, mod 10 (A = 2, …, Z = 7). */
private fun upsCharacterValue(character: Char): Int =
    if (character in '0'..'9') character - '0' else ((if (character.code < 0x80) character.code else 63) - 63) % 10

/** Weights 1, 2, 1, … from the left, plain products, and ten's complement. */
private fun upsCheckDigit(payload: String): Int {
    val sum = payload.withIndex().sumOf { (offset, character) ->
        upsCharacterValue(character) * (if (offset % 2 == 0) 1 else 2)
    }
    return (10 - sum % 10) % 10
}

/** UPS `1Z` numbers: fifteen characters and a check digit over those fifteen. */
fun CarrierCatalog.Companion.isValidUpsTrackingNumber(value: String): Boolean {
    if (!matches(value, "^1Z[0-9A-Z]{15}[0-9]$")) return false
    return upsCheckDigit(value.drop(2).take(15)) == value.last() - '0'
}

/** OnTrac's `C` or `D` and fourteen digits: the letter counts as 4 or 5 in the UPS check. */
fun CarrierCatalog.Companion.isValidOnTracTrackingNumber(value: String): Boolean {
    if (!matches(value, "^[CD][0-9]{14}$")) return false
    return upsCheckDigit(value.take(14)) == value.last() - '0'
}

/**
 * Evri 16-character parcel numbers: the first fifteen characters, valued as UPS values letters, weigh 2, 1,
 * 2, … from the left; the sum mod 10 is the last digit.
 */
fun CarrierCatalog.Companion.isValidEvriParcelNumber(value: String): Boolean {
    if (!matches(value, "^[HT][0-9A-Z]{5}[0-9]{10}$")) return false
    val sum = value.take(15).withIndex().sumOf { (offset, character) ->
        upsCharacterValue(character) * (if (offset % 2 == 0) 2 else 1)
    }
    return sum % 10 == value.last() - '0'
}

/** Colissimo's two-character product code, ten digits and their GS1 key. */
fun CarrierCatalog.Companion.isValidColissimoParcelNumber(value: String): Boolean =
    matches(value, "^[0-9A-Z]{2}[0-9]{11}$") && hasGs1CheckDigit(value.drop(2))

/**
 * SF Express waybills: after the three-digit area code, the serial weighs 1, 3, 5, … from the right; each
 * product adds its tens and units, and the last digit tops the sum up to a multiple of ten.
 */
fun CarrierCatalog.Companion.isValidSfExpressWaybill(value: String): Boolean {
    if (!matches(value, "^(?:[0-9]{12}|SF[0-9]{13})$")) return false
    val digits = (if (value.startsWith("SF")) value.drop(2) else value).digits()
    var sum = 0
    for ((offset, digit) in digits.slice(3 until digits.size - 1).reversed().withIndex()) {
        val product = digit * (2 * offset + 1)
        sum += product / 10 + product % 10
    }
    return (10 - sum % 10) % 10 == digits[digits.size - 1]
}

/** An SSCC behind its GS1 application identifier `00`. Shippers issue SSCCs, so a valid one names no carrier. */
fun CarrierCatalog.Companion.isValidSscc(value: String): Boolean =
    matches(value, "^00[0-9]{18}$") && hasGs1CheckDigit(value)

fun CarrierCatalog.Companion.isValidDhlExpressWaybill(value: String): Boolean {
    if (!matches(value, "^[0-9]{10}$")) return false
    val serial = value.take(9).toLongOrNull() ?: return false
    return serial % 7 == (value.last() - '0').toLong()
}

fun CarrierCatalog.Companion.isValidTntConsignmentNumber(value: String): Boolean {
    if (!matches(value, "^[0-9]{9}$")) return false
    val serial = value.take(8).toLongOrNull() ?: return false
    val check = value.last() - '0'
    if (serial % 7 == check.toLong()) return true
    val digits = value.digits()
    val weights = intArrayOf(8, 6, 4, 2, 3, 5, 9, 7)
    val sum = weights.withIndex().sumOf { (offset, weight) -> digits[offset] * weight }
    val digit = 11 - sum % 11
    return (if (digit == 11) 5 else if (digit == 10) 0 else digit) == check
}

fun CarrierCatalog.Companion.isValidPocztaPolskaBarcode(value: String): Boolean {
    if (!matches(value, "^[0-9]{20}$")) return false
    val digits = value.digits()
    val sum = (0 until 19).sumOf { digits[it] * (if (it % 2 == 0) 3 else 1) }
    return (10 - sum % 10) % 10 == digits[19]
}

/**
 * Correos parcel and expedition codes: the summed character codes before the last letter pick it from the
 * tax-id letter table.
 */
fun CarrierCatalog.Companion.isValidCorreosSpainCheckLetter(value: String): Boolean {
    val letters = "TRWAGMYFPDXBNJZSQVHLCKE"
    val characters = value.characters()
    if (characters.size <= 1) return false
    val sum = characters.dropLast(1).joinToString("").codePoints().asLongStream().sum()
    return letters[(sum % letters.length).toInt()].toString() == characters.last()
}

/** DPD parcel labels: fourteen digits and the ISO/IEC 7064 MOD 37,36 check character printed after them. */
fun CarrierCatalog.Companion.isValidDpdParcelNumber(value: String): Boolean {
    if (!matches(value, "^[0-9]{14}[0-9A-Z]$")) return false
    val alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var remainder = 36
    for (digit in value.take(14).digits()) {
        remainder += digit
        if (remainder > 36) remainder -= 36
        remainder *= 2
        if (remainder > 36) remainder -= 37
    }
    return alphabet[(37 - remainder) % 36] == value.last()
}

/**
 * The ninth of nine digits checks the first eight: weights 8, 6, 4, 2, 3, 5, 9, 7, then 11 minus the sum
 * mod 11, where 10 becomes 0 and 11 becomes 5.
 */
private fun hasS10CheckDigit(digits: String): Boolean {
    if (digits.length != 9 || !digits.all { it in '0'..'9' }) return false
    val values = digits.digits()
    val weights = intArrayOf(8, 6, 4, 2, 3, 5, 9, 7)
    val sum = weights.withIndex().sumOf { (offset, weight) -> values[offset] * weight }
    val rawCheck = 11 - sum % 11
    val expected = if (rawCheck == 10) 0 else if (rawCheck == 11) 5 else rawCheck
    return values[8] == expected
}

fun CarrierCatalog.Companion.isValidS10(raw: String): Boolean {
    val value = normalize(raw)
    if (!matches(value, "^[A-Z]{2}[0-9]{9}[A-Z]{2}$")) return false
    return hasS10CheckDigit(value.drop(2).take(9))
}

/**
 * A Pos Laju consignment: three letters, nine digits and MY. The ninth digit is the S10 check digit of the
 * eight before it; the extra letter is outside the check.
 */
fun CarrierCatalog.Companion.isValidPosLajuConsignment(value: String): Boolean =
    matches(value, "^[A-Z]{3}[0-9]{9}MY$") && hasS10CheckDigit(value.drop(3).take(9))

fun CarrierCatalog.Companion.supportsSwissPostHandoff(raw: String): Boolean {
    val value = normalize(raw)
    return matches(value, "^L[A-Z]\\d{9}CH$") && isValidS10(value)
}

/** USPS package barcodes: only the PIC participates in MOD10; a routing ZIP precedes it. */
fun CarrierCatalog.Companion.isValidUspsPackageBarcode(raw: String): Boolean {
    val number = normalize(raw)
    fun validPic(pic: String): Boolean {
        // Retail 95 and legacy 91 are read only at 22 digits, so a ZIP+4 add-on never splits ambiguously.
        if (!matches(pic, "^(?:9[1-5][0-9]{20}|9[2-4][0-9]{24})$")) return false
        val digits = pic.digits()
        var sum = 0
        for (offset in digits.indices) {
            sum += digits[digits.size - 1 - offset] * (if (offset % 2 == 0) 1 else 3)
        }
        return sum % 10 == 0
    }
    if (validPic(number)) return true
    // The ship-to AI 420 and a ZIP5 or ZIP9 before a 22- or 26-digit PIC, by length alone.
    if (!matches(number, "^420(?:[0-9]{5}|[0-9]{9})(?:[0-9]{22}|[0-9]{26})$")) return false
    val readings = listOf(number.drop(8), number.drop(12)).filter(::validPic)
    if (readings.size < 2) return readings.size == 1
    // A 34-digit scan fits ZIP5/PIC26 and ZIP9/PIC22. When both pass, the one whose Mailer ID fits its
    // channel wins: nine digits starting with 9 after 92 and the legacy 91's service code, six starting
    // with 0 to 8 after 93. If both or neither fit, the split is rejected.
    return readings.count { matches(it, "^(?:91[0-9]{2}9|92[0-9]{3}9|93[0-9]{3}[0-8]|9[45])") } == 1
}

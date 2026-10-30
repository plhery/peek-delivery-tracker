"""Cuts Noto Sans SC Regular to the characters the server's pictures write in Chinese.

GB2312's characters and symbols, CJK punctuation, full-width forms and every character of the Chinese copy, without
hinting. Run it with fontTools (`pip install fonttools`) from the repository's root:

    python3 scripts/subset-chinese-face.py NotoSansSC-Regular.otf public/fonts/noto-sans-sc/NotoSansSC-Regular.otf
"""
import json
import sys

from fontTools import subset
from fontTools.ttLib import TTFont

source, target = sys.argv[1], sys.argv[2]
characters = set()
# GB2312's rows 0xA1-0xA9 hold its punctuation and symbols, 0xB0-0xF7 its 6,763 characters.
for row in [*range(0xA1, 0xAA), *range(0xB0, 0xF8)]:
    for cell in range(0xA1, 0xFF):
        try:
            characters.add(bytes([row, cell]).decode('gb2312'))
        except UnicodeDecodeError:
            pass
for path in ['shared/locales/zh.json', 'shared/demo-locales/zh.json']:
    for value in json.load(open(path, encoding='utf-8')).values():
        characters.update(value)
characters.update(chr(code) for code in [*range(0x3000, 0x3040), *range(0xFF01, 0xFF5F)])

options = subset.Options()
options.layout_features = ['*']
options.hinting = False
options.name_IDs = ['*']
options.notdef_outline = True
font = TTFont(source)
known = font.getBestCmap()
subsetter = subset.Subsetter(options)
subsetter.populate(unicodes=sorted(ord(character) for character in characters if ord(character) in known))
subsetter.subset(font)
# Every character kept is in the Basic Multilingual Plane, which the format 4 map holds and the server reads.
font['cmap'].tables = [table for table in font['cmap'].tables if table.format == 4]
font.save(target)

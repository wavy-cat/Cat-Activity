import json
from time import time
from io import BytesIO
from os import listdir, path, makedirs

import cairosvg
from PIL import Image
from tqdm import tqdm

time_start = time()

# Color name – background hex
colors = {
    'Frappe': '#303446',
    'Latte': '#eff1f5',
    'Macchiato': '#24273a',
    'Mocha': '#1e1e2e'
}

# An original name – Server name
with open('icons.json', 'r') as file:
    icons = json.load(file)

# Creating the required folder structure
for color in colors.keys():
    if not path.exists('web/' + color):
        makedirs('web/' + color)

    if not path.exists('web/IDE/' + color):
        makedirs('web/IDE/' + color)

for theme, background in colors.items():
    print('* Generating icons ' + theme)
    for original_icon, cat_icon in tqdm(icons.items()):
        image_icon = Image.open(BytesIO(cairosvg.svg2png(url=f'vscode-icons/icons/{theme.lower()}/{original_icon}.svg',
                                                         output_height=1024, output_width=1024)))
        image_full = Image.new('RGBA', (1536, 1536), background)

        image_full.paste(image_icon, (256, 256), image_icon)
        image_full.save(f'web/{theme}/{cat_icon}.png')

    print('* Generating IDE icons ' + theme)
    for file in tqdm(listdir('ide-icons')):
        image_icon = Image.open(BytesIO(cairosvg.svg2png(url=f'ide-icons/{file}',
                                                         output_height=1024, output_width=1024)))
        image_full = Image.new('RGBA', (1536, 1536), background)

        image_full.paste(image_icon, (256, 256), image_icon)
        image_full.save(f'web/IDE/{theme}/{file.lower().removesuffix('_icon.svg')}.png')

print('[✔] Done. Total time spent:', time() - time_start)

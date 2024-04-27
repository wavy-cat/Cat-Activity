import json
from shutil import copy
from time import time
from io import BytesIO
from os import listdir, path, makedirs
from multiprocessing import Process

import cairosvg
from PIL import Image
from tqdm import tqdm

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


def generate_icons(theme: str, background: str, position: int):
    for original_icon, cat_icon in tqdm(icons.items(), position=position, desc='Generating icons ' + theme):
        image_icon = Image.open(BytesIO(cairosvg.svg2png(url=f'vscode-icons/icons/{theme.lower()}/{original_icon}.svg',
                                                         output_height=1024, output_width=1024)))
        image_full = Image.new('RGBA', (1536, 1536), background)

        image_full.paste(image_icon, (256, 256), image_icon)
        image_full.save(f'web/{theme}/{cat_icon}.png')


def generate_ide_icons(theme: str, background: str, position: int):
    for file in tqdm(listdir('ide-icons'), position=position, desc='Generating IDE icons ' + theme):
        image_icon = Image.open(BytesIO(cairosvg.svg2png(url=f'ide-icons/{file}',
                                                         output_height=1024, output_width=1024)))
        image_full = Image.new('RGBA', (1536, 1536), background)

        image_full.paste(image_icon, (256, 256), image_icon)
        image_full.save(f'web/IDE/{theme}/{file.lower().removesuffix("_icon.svg")}.png')


# Creating the required folder structure
for color in colors.keys():
    if not path.exists('web/' + color):
        makedirs('web/' + color)

    if not path.exists('web/IDE/' + color):
        makedirs('web/IDE/' + color)

    if not path.exists('web/index.html'):
        copy('index.html', 'web/index.html')

if __name__ == '__main__':
    time_start, processes = time(), []

    for theme, background in colors.items():
        p1 = Process(target=generate_icons, args=(theme, background, len(processes)), daemon=True)
        p2 = Process(target=generate_ide_icons, args=(theme, background, len(processes) + 1), daemon=True)
        p1.start()
        p2.start()

        processes += [p1, p2]

    for process in processes:
        process.join()

    print('[✔] Done. Total time spent:', time() - time_start)

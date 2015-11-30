import 'dart:html';

void main() {
    print("I am here");
    querySelector('#test_input').onInput.listen(updateOutput);
}

void updateOutput(Event e) {
    querySelector('#test_output').text = (e.target as InputElement).value;
}
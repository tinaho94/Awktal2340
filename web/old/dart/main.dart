import 'dart:html';

void main() {
    print("I am here");
    querySelector('#test_input').onInput.listen(updateOutput);
    var a = 1 + 2;
    print("I am $a");
}

void updateOutput(Event e) {
    querySelector('#test_output').text = (e.target as InputElement).value;
}